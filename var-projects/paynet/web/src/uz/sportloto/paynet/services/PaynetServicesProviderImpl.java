package uz.sportloto.paynet.services;

import net.sf.hibernate.HibernateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.time.DateUtils;
import uz.sportloto.paynet.comm.PaynetCommunicationImpl;
import uz.sportloto.paynet.comm.PaynetCommException;
import uz.sportloto.paynet.dao.DAOFactory;
import uz.sportloto.paynet.dao.ProvidersDAO;
import uz.sportloto.paynet.dao.PaymentDAO;
import uz.sportloto.paynet.model.PaynetRequest;
import uz.sportloto.paynet.model.response.Provider;
import uz.sportloto.paynet.model.response.Service;
import uz.sportloto.paynet.transform.PaynetTransformerFactory;
import uz.sportloto.paynet.transform.PaynetResponseTransformer;
import uz.sportloto.paynet.transform.TransformException;
import uz.sportloto.Configuration;

import java.util.*;

/**
 * Интерфейс доступа к спискам провайдеров и сервисов. Скрывает за собой ВСЁ :-)
 */
public class PaynetServicesProviderImpl implements PaynetServicesProvider{
    /** Logger */
    private static final Log log = LogFactory.getLog(PaynetServicesProviderImpl.class);

    /** Fields */
    private List<Provider> providersList;
    private Map<Integer, Service> servicesMap;

    /** IoC */
    private ProvidersDAO providersDao = DAOFactory.getProvidersDAO();
    private PaynetCommunicationImpl paynetCommunication = new PaynetCommunicationImpl();

    /**
     * Возвращает сервис по его идентификатору
     * @param serviceId идентификатор сервиса
     * @return сервис
     */
    public Service getServiceById(Integer serviceId) {
        if(servicesMap == null){
            try {
                servicesMap = providersDao.loadServicesMap();
            }
            catch (HibernateException e) {
                log.error("Cannot load services!",e);
                servicesMap = new HashMap<Integer, Service>();
            }
        }
        return servicesMap.get(serviceId);
    }

    /**
     * Возвращает всех провайдеров в одной коллекциии.
     * @param paynetRequest такой запрос может возникнуть только в случае если терминал запрашивает список провайдеров.
     * @return коллекция провайдеров
     */
    public Collection<Provider> getAllProviders(PaynetRequest paynetRequest) {
        refershProviders(paynetRequest);
        if(providersList == null){
            providersList = new ArrayList<Provider>();
            final List<Provider> list;
            try {
                list = DAOFactory.getProvidersDAO().loadProvidersList();
                providersList.addAll(list);
            }
            catch (HibernateException e) {
                log.error("Cannot load providers from DB!",e);
            }
        }
        return providersList;
    }

    /**
     * Метод обновляет провайдеров в базе данных при необходимости. Правила по которым
     * провайдеры должны обновляться прописаны в этом методе и предполагается что будут вынесены
     * в конфигурацию
     * @param paynetRequest запрос на обновление
     */
    private void refershProviders(PaynetRequest paynetRequest) {
        boolean needsUpdate = true;
        try {
            final Date updatedOn = DAOFactory.getProvidersDAO().getProvidersUpdatedOn();
            // если последний раз база обновлялась не сегодня - то обновить из пейнета.
            needsUpdate = !DateUtils.isSameDay(new Date(), updatedOn);
        }
        catch (HibernateException e) {
            log.error("Cannot load providers from DB!",e);
        }

        if(needsUpdate || Configuration.getForceProvidersUpdate()){
            log.info("Updating providers list from paynet.");
            try {
                paynetCommunication.queryPaynet(paynetRequest);
                final PaynetResponseTransformer trans = PaynetTransformerFactory.getTransformer(1);
                trans.transform(paynetRequest);
                providersDao.saveEntireProvidersList((Collection<Provider>) trans.getAsObject());
            }
            catch (PaynetCommException e) {
                log.error("Cannot get providers from paynet!",e);
            } catch (TransformException e) {
                log.error("Cannot transform response from paynet",e);
            } catch (HibernateException e) {
                log.error("Cannot save all providers",e);
            }
        }
        else{
            log.info("No need to update providers list.");
        }
    }
}