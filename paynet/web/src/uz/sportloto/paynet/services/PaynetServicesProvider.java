package uz.sportloto.paynet.services;

import uz.sportloto.paynet.model.PaynetRequest;
import uz.sportloto.paynet.model.response.Provider;
import uz.sportloto.paynet.model.response.Service;

import java.util.Collection;

/**
 * Интерфейс доступа к спискам провайдеров и сервисов. Скрывает за собой ВСЁ :-)
 */
public interface PaynetServicesProvider {
    /**
     * Метод возвращет услугу по её идентификатору
     * @param serviceId идентефикатор услуги
     * @return собссно услуга
     */
    public Service getServiceById(Integer serviceId);

    /**
     * Метод возвращает полный список всех провайдеров
     * @param paynetRequest запрос которым запросили список провайдеров
     * @return список провайдеров
     */
    public Collection<Provider> getAllProviders(PaynetRequest paynetRequest);
}
