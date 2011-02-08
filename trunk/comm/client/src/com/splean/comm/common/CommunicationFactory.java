package com.splean.comm.common;

import com.splean.comm.fake.CommunicationsFake;

/**
 * Фабрика объектов связи. позже сделать так чтобы возвращала реальный объект а не фейк
 */
public class CommunicationFactory {
    /**
     * @return интерфейс для коммуникации с сервером
     */
    public static Communications getComm(){
        return new CommunicationsFake();
    }
}
