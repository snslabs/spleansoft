package com.splean.comm.common;

import java.io.*;

/**
 * Класс для пересылки ответа
 */
public class Response implements Serializable {
    private byte[] responseBody = null;
    transient public static final int BUFFER_SIZE = 2048; // размер буфера - 4 килобайта

    public Response(InputStream stream_) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int cnt = 0;
        int pos = 0;
        byte[] tmp;
        // читаем поток в буфер
        BufferedInputStream stream = new BufferedInputStream(stream_,2048);
        while((cnt = stream.read(buffer, 0, BUFFER_SIZE))!=-1){
            // создаём массив с длинной равной текущему количеству данных считанных из потока
            tmp = new byte[cnt+pos];
            // если имелись уже считанные данные, то копируем их в новый массив
            if(responseBody!=null){
                System.arraycopy(responseBody,0,tmp,0,responseBody.length);
            }
            // копируем в новый массив данные из буфера
            System.arraycopy(buffer, 0, tmp, pos, cnt);
            // сохраняем массив в качестве поля
            responseBody = tmp;
            pos += cnt;
        }
        System.out.println("Response created. Length: "+responseBody.length);
    }

    public void setResponseBody(byte[] responseBody) {
        this.responseBody = responseBody;
    }

    public byte[] getResponseBody() {
        return responseBody;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(responseBody);
    }

    public String toString() {
        return responseBody==null?"null":new String(responseBody);
    }
}
