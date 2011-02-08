package com.splean.comm.common;

import com.splean.comm.fake.CommunicationsFake;

/**
 * ������� �������� �����. ����� ������� ��� ����� ���������� �������� ������ � �� ����
 */
public class CommunicationFactory {
    /**
     * @return ��������� ��� ������������ � ��������
     */
    public static Communications getComm(){
        return new CommunicationsFake();
    }
}
