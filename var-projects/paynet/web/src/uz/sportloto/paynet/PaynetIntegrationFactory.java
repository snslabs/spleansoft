package uz.sportloto.paynet;

import uz.sportloto.paynet.comm.PaynetCommunicationImpl;
import uz.sportloto.paynet.services.PaynetServicesProviderImpl;

import java.util.Map;

/**
 * Factory that provides paynet integration instances
 */
public class PaynetIntegrationFactory {
    /**
     * Создаёт нового интегратора
     * @param paynetTerminalId - код терминала paynet
     * @param requestParameters - все переданые от терминала параметры
     * @return интегратор
     */
    public static PaynetIntegration createIntegration(String paynetTerminalId, Map<String, String[]> requestParameters){
        final PaynetIntegrationImpl paynetIntegration = new PaynetIntegrationImpl(paynetTerminalId, requestParameters);
        paynetIntegration.setPaynetComm(new PaynetCommunicationImpl());
        paynetIntegration.setPaynetServicesProvider(new PaynetServicesProviderImpl());
        return paynetIntegration;
    }
}
