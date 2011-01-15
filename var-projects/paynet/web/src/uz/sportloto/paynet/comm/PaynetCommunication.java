package uz.sportloto.paynet.comm;

import uz.sportloto.paynet.model.PaynetRequest;

/**
 * Интерфейс коммуникации с пейнетом
 */
public interface PaynetCommunication {
    /**
     * Запрос в пейнет
     * @param paynetRequest  модель запроса. Ответ пейнета в чистом виде - как он получен от сервера (XML) сохраняется в модели запроса
     * @throws PaynetCommException если возникли проблемы по установлению связи с пейнетом
     */
    public void queryPaynet(PaynetRequest paynetRequest) throws PaynetCommException;
}
