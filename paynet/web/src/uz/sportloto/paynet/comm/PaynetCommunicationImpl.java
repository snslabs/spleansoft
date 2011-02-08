package uz.sportloto.paynet.comm;

import net.sf.hibernate.HibernateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uz.sportloto.Configuration;
import uz.sportloto.paynet.dao.DAOFactory;
import uz.sportloto.paynet.dao.PaymentDAO;
import uz.sportloto.paynet.dao.SecurityDAO;
import uz.sportloto.paynet.model.PaynetRequest;
import uz.sportloto.paynet.model.PaynetRequestDetail;
import uz.sportloto.paynet.model.PaynetCredentials;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Реализация связи с пейнетом. <b>Not threadsafe!!!</b>
 */
public class PaynetCommunicationImpl implements PaynetCommunication {
    char[] buf = new char[1024];
    private static final Log log = LogFactory.getLog(PaynetCommunicationImpl.class);
    private SecurityDAO securityDao = DAOFactory.getSecurityDAO();

    /**
     * Запрос в пейнет
     * @param paynetRequest  модель запроса. После успешного выполнения запроса модель заполняется ответом.
     * @throws PaynetCommException если возникли проблемы по установлению связи с пейнетом
     */
    public void queryPaynet(PaynetRequest paynetRequest) throws PaynetCommException {

        paynetRequest.setUrl( getBaseURL(paynetRequest.getActionCode()) + getQueryString(paynetRequest) );
        final PaymentDAO dao = DAOFactory.getPaymentDAO();
        try {
            dao.savePayment(paynetRequest);
        } catch (HibernateException e) {
            log.error("Cannot save paynet request.",e);
        }
        log.debug("PAYNET REQUEST: " + paynetRequest.getUrl());
        try {
            final URL url = new URL(paynetRequest.getUrl());
            final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            StringBuffer sb = new StringBuffer();
            InputStreamReader streamReader = new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
            int len;
            while ((len = streamReader.read(buf)) != -1) {
                sb.append(buf, 0, len);
            }

            log.debug("PAYNET RESPONSE: " + sb.toString());
            // устанавливаем результат ответа
            paynetRequest.setResponse(sb.toString());
        }
        catch (IOException e) {
            log.error("Error communication with paynet",e);
            paynetRequest.setResponse(e.getMessage());
            paynetRequest.setState('F'); // Failed to be sent to Paynet
            try {
                dao.savePayment(paynetRequest);
            } catch (HibernateException ex) {
                log.error("Cannot save paynet request.",ex);
            }
            throw new PaynetCommException("Error communication with paynet",e);
        }
        finally{
            try {
                dao.savePayment(paynetRequest);
            } catch (HibernateException ex) {
                log.error("Cannot save paynet request.",ex);
            }
        }
    }

    private String getQueryString(PaynetRequest paynetRequest) throws PaynetCommException {
        StringBuffer sb = new StringBuffer("?");
        PaynetCredentials paynetCredentials = null;
        try{
            paynetCredentials = securityDao.getPaynetCredentialsByTerminalId(Integer.valueOf(paynetRequest.getTerminalId()));
            if(paynetCredentials == null ){
                log.error("No data found for terminal_id = " + paynetRequest.getTerminalId() + " (DAO return NULL)");
                throw new PaynetCommException("Терминал PAYNET_ID = " + paynetRequest.getTerminalId()+ " не найден!");
            }
            sb.append("USERNAME=").append(paynetCredentials.getPaynetLogin()).append("&").
                    append("PASSWORD=").append(paynetCredentials.getPaynetPwd()).append("&").
                    append("ACT=").append(paynetRequest.getActionCode()).append("&").
                    append("TERMINAL_ID=").append(paynetRequest.getTerminalId()).append("&");
        }
        catch(HibernateException e){
            log.error("Cannot obtain paynet credentials for terminal_id=" + paynetRequest.getTerminalId(), e);
            throw new PaynetCommException("Cannot get paynet credentials for terminal_id = " + paynetRequest.getTerminalId(), e);
        }
// todo: remove at all or use switch to use old method of obtaining username and password for paynet transaction
//        sb.append("USERNAME=").append(Configuration.getPaynetUsername()).append("&").
//                append("PASSWORD=").append(Configuration.getPaynetPassword()).append("&").
//                append("ACT=").append(paynetRequest.getActionCode()).append("&").
//                append("TERMINAL_ID=").append(paynetRequest.getTerminalId()).append("&");
        switch(paynetRequest.getActionCode()){
            case 2:
                sb.append("SERVICE_ID=").append(paynetRequest.getServiceId()).append("&");
                for (PaynetRequestDetail requestDetail : paynetRequest.getDetails()) {
                   sb.append(requestDetail.getFieldName()).append("=").append(requestDetail.getFieldValue()).append("&");
                }
                break;
            case 4:
                sb.append("REPORT_DATE=").append(paynetRequest.getReportDate());
                break;
        }

        if(log.isDebugEnabled()){
            log.debug("QueryString constructed: " + sb.toString());
        }
        return sb.toString();
    }

    private String getBaseURL(Integer actionCode) {
        switch(actionCode){
            case 1: return Configuration.getProvidersUrl();
            case 2: return Configuration.getPayUrl();
            case 3: return Configuration.getLastTransactionUrl();
            case 4: return Configuration.getDayReportUrl();
            default: throw new IllegalArgumentException("No URL found for actionCode="+actionCode);
        }
    }


    // статическая инициализация https - если включен параметр paynet.plindtrust то наличие установленого
    // SSL сертефиката не требуется
     static {
        if (Configuration.isBlindTrust()) {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Install the all-trusting trust manager
            try {
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            } catch (Exception e) {
                e.printStackTrace();
            }

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        }

    }

}
