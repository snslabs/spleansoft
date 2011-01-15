package com.splean.comm.common;

public interface Communications {
    /**
     * отправить сообщение. ƒолжны присутствовать все параметры
     * @param msgBody текст сообщени€
     * @param to от кого
     * @param from кому
     * @return true если отправлено удачно, false - если нет
     * @throws Exception непредвиденна€ ситуаци€
     */
    boolean sendMessage(String msgBody, Subscriber to, Subscriber from) throws Exception;

    /**
     * отправить сообщение
     * @param msg модель сообщени€. ƒолжны быть заполнены все пол€
     * @return true если отправлено удачно, false - если нет
     * @throws Exception непредвиденна€ ситуаци€
     */
    boolean sendMessage(CommMessage msg) throws Exception;

    /**
     * ѕолучить список новых сообщений с сервера. —ообщени€ на сервере удал€ютс€
     * @param to адресат
     * @return массив моделей сообщений
     * @throws Exception непредвиденна€ ситуаци€
     */
    CommMessage[] checkMessages(Subscriber to) throws Exception;

    /**
     * ѕолучить список новых сообщений с сервера. —ообщени€ на сервере удал€ютс€
     * @param to адресат
     * @param clearFromServer удал€ть сообщени€ с сервера
     * @return массив моделей сообщений
     * @throws Exception непредвиденна€ ситуаци€
     */
    CommMessage[] checkMessages(Subscriber to, boolean clearFromServer) throws Exception;

}
