package uz.sportloto.paynet.dao;

import net.sf.hibernate.HibernateException;
import uz.sportloto.paynet.model.response.Provider;
import uz.sportloto.paynet.model.response.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Date;

public interface ProvidersDAO {
    /**
     * Сохраняет всю модель провайдеров представленую в виде списка провайдеров List&lt;Provider>
     * @param allProviders - all providers in single collection
     * @throws net.sf.hibernate.HibernateException if anything wrong with hibernate
     */
    void saveEntireProvidersList(Collection<Provider> allProviders) throws HibernateException;

    /**
     * Загружает список провайдеров из базы
     * @return list of all providers
     * @throws net.sf.hibernate.HibernateException if anything wrong with hibernate
     */
    List<Provider> loadProvidersList() throws HibernateException;

    /**
     * Загружает карту всех сервисов
     * @return карта сервисов
     * @throws net.sf.hibernate.HibernateException  if anything wrong with hibernate
     */
    Map<Integer, Service> loadServicesMap() throws HibernateException;

    /**
     * Возвращает дату обновления провайдеров в системе
     * @return дату обновления провайдеров
     * @throws HibernateException if anything wrong with hibernate
     */
    Date getProvidersUpdatedOn() throws HibernateException;
}
