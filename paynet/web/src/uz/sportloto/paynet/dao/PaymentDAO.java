package uz.sportloto.paynet.dao;

import uz.sportloto.paynet.model.PaynetRequest;
import net.sf.hibernate.HibernateException;

public interface PaymentDAO {
    /**
     * Сохраняем модель запроса в базе
     * @param paynetRequest модель запроса для сохранения
     * @throws HibernateException если что-то не так с базой
     */
    void savePayment(PaynetRequest paynetRequest) throws HibernateException;

    /**
     * Поиск запроса по внтуреннему номеру чека или по номеру транзакции Paynet'а. Поиск осуществляется по полям
     * internal_cheque_id и paynet_cheque_id через условие or, т.е. можно указать только одно из полей
     * @param internalChequeId внтуренний номер чека
     * @param paynetChequeId номер транзакции Paynet'a
     * @return модель запроса загруженая из базы, или null если не найдено
     * @throws HibernateException если что-то не так с базой
     */
    PaynetRequest findPaynetRequestByChequeId(String internalChequeId, String paynetChequeId) throws HibernateException;

    /**
     * Метод проверяет существует ли в базе уже запрос с таким же номером внутреннего чека internal_cheque_id.
     * Это необходимо для предотвращения повторной отправки данных
     * @param paynetRequest модель запроса в пейнет
     * @return true - если такой платёж уже был отправлен, false - если нет
     * @throws net.sf.hibernate.HibernateException если что-то не так с базой
     */
    boolean isDuplicate(PaynetRequest paynetRequest) throws HibernateException;
}
