package com.splean.comm.common;

import java.io.*;

/**
 * ����� ��� ��������� ������
 */
public class Response implements Serializable {
    private byte[] responseBody = null;
    transient public static final int BUFFER_SIZE = 2048; // ������ ������ - 4 ���������

    public Response(InputStream stream_) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int cnt = 0;
        int pos = 0;
        byte[] tmp;
        // ������ ����� � �����
        BufferedInputStream stream = new BufferedInputStream(stream_,2048);
        while((cnt = stream.read(buffer, 0, BUFFER_SIZE))!=-1){
            // ������ ������ � ������� ������ �������� ���������� ������ ��������� �� ������
            tmp = new byte[cnt+pos];
            // ���� ������� ��� ��������� ������, �� �������� �� � ����� ������
            if(responseBody!=null){
                System.arraycopy(responseBody,0,tmp,0,responseBody.length);
            }
            // �������� � ����� ������ ������ �� ������
            System.arraycopy(buffer, 0, tmp, pos, cnt);
            // ��������� ������ � �������� ����
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
