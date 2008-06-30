package uz.sportloto.paynet;

import net.sf.hibernate.HibernateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uz.sportloto.paynet.comm.PaynetCommException;
import uz.sportloto.paynet.comm.PaynetCommunication;
import uz.sportloto.paynet.dao.DAOFactory;
import uz.sportloto.paynet.dao.PaymentDAO;
import uz.sportloto.paynet.model.PaynetRequest;
import uz.sportloto.paynet.model.PaynetRequestDetail;
import uz.sportloto.paynet.model.response.Field;
import uz.sportloto.paynet.model.response.Service;
import uz.sportloto.paynet.services.PaynetServicesProvider;
import uz.sportloto.paynet.transform.PaynetResponseTransformer;
import uz.sportloto.paynet.transform.PaynetTransformerFactory;
import uz.sportloto.paynet.transform.TransformException;
import uz.sportloto.paynet.transform.impl.CustomErrorTransformer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.math.BigDecimal;

public class PaynetIntegrationImpl implements PaynetIntegration {
    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(PaynetIntegrationImpl.class);

    /**
     * Fields
     */
    private Map<String, String[]> requestParameters;
    private String terminalId;

    /**
     * IoC
     */
    private PaynetCommunication paynetComm;
    private PaynetServicesProvider paynetServicesProvider;

    /**
     * Constants
     */
    private static String USERNAME = "USERNAME";
    private static String PASSWORD = "PASSWORD";
    private static String PAYNET_TERMINAL_ID = "PAYNET_TERMINAL_ID";
    private static String REPORT_DATE = "REPORT_DATE";
    private static final String SERVICE_ID = "SERVICE_ID";

    private static final int PROVIDERS_ACTION = 1;
    private static final int PAY_ACTION = 2;
    private static final int LAST_TRANSACTION_ACTION = 3;
    private static final int DAY_REPORT_ACTION = 4;
    private static final String ACT = "ACT";
    private static final String INTERNAL_CHEQUE_ID = "INTERNAL_CHEQUE_ID";

    /**
     * Оплата по PAYNET. Если необходима обработка ответа от пейнета - то она осуществляется тут же.
     *
     * @return ответ от пейнета готовый к отправке на терминал.
     * @throws MandatoryParameterMissing если отсутствует обязательный параметр
     */
    public String callPaynet() throws MandatoryParameterMissing, PaynetCommException, TransformException {

        // запрос в пейнет
        PaynetRequest paynetRequest = new PaynetRequest();
        // устанавливается именно PAYNET идентификатор терминала
        paynetRequest.setTerminalId(getRequestParameter(PAYNET_TERMINAL_ID, true));
        paynetRequest.setActionCode(Integer.parseInt(getRequestParameter(ACT, true)));

        // для кода 2 (платёж) необходимо передать доп параметры
        if (paynetRequest.getActionCode() == PAY_ACTION) {

            final Integer serviceId = Integer.valueOf(getRequestParameter(SERVICE_ID, true));
            final Service service = paynetServicesProvider.getServiceById(serviceId);

            paynetRequest.setServiceId(service.getServiceId());
            paynetRequest.setProviderId(service.getProvider().getProviderId());
            paynetRequest.setInternalChequeId(getRequestParameter(INTERNAL_CHEQUE_ID, false));
            paynetRequest.setSentOn(new Date());

            for (Field field : service.getServiceType().getFields()) {
                final String fieldName = field.getName();
                final String fieldValue = getRequestParameter(fieldName, field.isFieldRequired());
                if (fieldValue != null && !"".equals(fieldValue)) {
                    paynetRequest.getDetails().add(new PaynetRequestDetail(fieldName, fieldValue, paynetRequest));
                }
                if ("summa".equals(fieldName)) {
                    paynetRequest.setSum(new BigDecimal(fieldValue));
                } else if ("quantity".equals(fieldName)) {
                    paynetRequest.setQuantity(Integer.valueOf(fieldValue));
                    paynetRequest.setServicePrice(service.getServicePrice());
                }
            }

            if(paynetRequest.getQuantity() != null && paynetRequest.getServicePrice() != null){
                paynetRequest.setSum( paynetRequest.getServicePrice().multiply(new BigDecimal(paynetRequest.getQuantity())));
            }

            //~
        }

        // отчёт за день - необходимо передать дату
        else if (paynetRequest.getActionCode() == DAY_REPORT_ACTION) {
            paynetRequest.setReportDate(getRequestParameter(REPORT_DATE, true));
        }
        paynetRequest.setState('R'); // status - received from terminal

        final PaymentDAO dao = DAOFactory.getPaymentDAO();
        // сохраняем запрос полученый от терминала
        boolean duplicate = false;
        try {
            duplicate = dao.isDuplicate(paynetRequest);
            dao.savePayment(paynetRequest);
        } catch (HibernateException e) {
            log.warn("Cannot save paynetRequest " + paynetRequest + " because of ", e);
        }
        try {
            // если это запрос на получение списка провайдеров, то обрабатываем его другим образом
            if (paynetRequest.getActionCode() == PROVIDERS_ACTION) {
                return getProvidersListForLUA(paynetRequest);
            }

            // вызываем пейнет
            if(!duplicate){
                //  если это не дублирование запроса, то обрабатываем
                try{
                    paynetComm.queryPaynet(paynetRequest);
                    return transformPaynetResponse(paynetRequest);
                }
                catch(TransformException e){
                    return new CustomErrorTransformer(-9999, "Неизвестная ошибка при обработке ответа.").transform(paynetRequest);
                }
                catch(PaynetCommException e){
                    return new CustomErrorTransformer(-9999, "Ошибка при соединения с пейнетом. " + e.getMessage()).transform(paynetRequest);
                }
            }
            else{
                paynetRequest.setState('D'); // статус - дубликат
                return new CustomErrorTransformer(-9000, "Дублирование запроса").transform(paynetRequest);
            }
        }
        finally {
            // сохраняем ответ полученый от paynet после обработки
            try {
                dao.savePayment(paynetRequest);
            } catch (HibernateException e) {
                log.warn("Cannot save paynetRequest " + paynetRequest + " because of ", e);
            }
        }

    }

    /**
     * Метод возвращает значение первого параметра из списка с одинаковыми именами, или null если такого
     * параметра в запросе не было
     *
     * @param parameterName имя параметра
     * @param mandatory     признак обязательности параметра, если обязательный параметр не найден, то бросается exception
     * @return значение параметра или null если такого параметра не было передано
     * @throws MandatoryParameterMissing возникает если отсутствует обязательный параметр
     */
    private String getRequestParameter(String parameterName, boolean mandatory) throws MandatoryParameterMissing {
        final String[] strings = requestParameters.get(parameterName);
        if (strings != null) {
            return strings[0];
        } else if (mandatory) {
            throw new MandatoryParameterMissing("Missing mandatory parameter " + parameterName);
        } else {
            return null;
        }
    }

    private String transformPaynetResponse(PaynetRequest paynetRequest) throws TransformException {
        final PaynetResponseTransformer paynetResponseTransformer = PaynetTransformerFactory.getTransformer(paynetRequest.getActionCode());
        final String result = paynetResponseTransformer.transform(paynetRequest);
        if (log.isDebugEnabled()) {
            System.out.println("Response transformed  to :\n" + result);
        }
        return result;
    }

    /**
     * Создание нового интегратора для терминала с определённым кодом.
     *
     * @param paynetTerminalId  пейнет код терминала
     * @param requestParameters карта всех параметров переданных с терминала
     */
    PaynetIntegrationImpl(String paynetTerminalId, Map<String, String[]> requestParameters) {
        this.terminalId = terminalId;
        this.requestParameters = requestParameters;
    }

    public void setPaynetComm(PaynetCommunication paynetComm) {
        this.paynetComm = paynetComm;
    }

    /**
     * Метод возвращает список провайдеров в формате пригодном для непосредственного использования LUA
     *
     * @param paynetRequest запрос на список провайдеров
     * @return список провайдеров в формате LUA
     */
    private String getProvidersListForLUA(PaynetRequest paynetRequest) {
        try {
            StringBuffer sb = new StringBuffer("providers = {\n");

            for (Iterator iterator = (paynetServicesProvider.getAllProviders(paynetRequest)).iterator(); iterator.hasNext();)
            {
                sb.append(iterator.next());
                if (iterator.hasNext()) {
                    sb.append(",");
                }
            }
            sb.append("\n}");

            if (log.isDebugEnabled()) {
                log.debug("Paynet providers converted to :" + sb.toString());
            }
            return sb.toString();
        }
        catch (Throwable e) {
            log.error("Cannot obtain providers.xml from paynet:", e);
        }
        // если ничего не получилось, то возвращаем пустой список провайдеров
        return "providers = {}";
    }

    public void setPaynetServicesProvider(PaynetServicesProvider paynetServicesProvider) {
        this.paynetServicesProvider = paynetServicesProvider;
    }
}
