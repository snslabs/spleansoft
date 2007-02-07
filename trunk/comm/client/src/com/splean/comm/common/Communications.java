package com.splean.comm.common;

public interface Communications {
    /**
     * ��������� ���������. ������ �������������� ��� ���������
     * @param msgBody ����� ���������
     * @param to �� ����
     * @param from ����
     * @return true ���� ���������� ������, false - ���� ���
     * @throws Exception �������������� ��������
     */
    boolean sendMessage(String msgBody, Subscriber to, Subscriber from) throws Exception;

    /**
     * ��������� ���������
     * @param msg ������ ���������. ������ ���� ��������� ��� ����
     * @return true ���� ���������� ������, false - ���� ���
     * @throws Exception �������������� ��������
     */
    boolean sendMessage(CommMessage msg) throws Exception;

    /**
     * �������� ������ ����� ��������� � �������. ��������� �� ������� ���������
     * @param to �������
     * @return ������ ������� ���������
     * @throws Exception �������������� ��������
     */
    CommMessage[] checkMessages(Subscriber to) throws Exception;

    /**
     * �������� ������ ����� ��������� � �������. ��������� �� ������� ���������
     * @param to �������
     * @param clearFromServer ������� ��������� � �������
     * @return ������ ������� ���������
     * @throws Exception �������������� ��������
     */
    CommMessage[] checkMessages(Subscriber to, boolean clearFromServer) throws Exception;

}
