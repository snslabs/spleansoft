package ru.snslabs.icq;

import org.jcq2k.MessagingNetwork;
import org.jcq2k.MessagingNetworkException;
import org.jcq2k.icq2k.ICQ2KMessagingNetwork;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import ru.snslabs.icq.model.ContactList;
import ru.snslabs.icq.model.Message;

/**
 * ����� ��� ������ � ICQ
 */
public class ICQGate {
    private static final Log log = LogFactory.getLog(ICQGate.class);

    private MessagingNetwork plugin;
    private String status = "offline";
    private ContactList contactList = new ContactList();

    public String getLogin() {
        return login;
    }

    private String login;
    private String password;
    List messages;

    /**
     * �����������
     * @param login �����
     * @param password ������
     */
    public ICQGate(String login, String password) {
        messages = new ArrayList();
        this.login = login;
        this.password = password;
        plugin = new ICQ2KMessagingNetwork();
        plugin.init();
    }

    /**
     * ���������. ���� �� ������� - �������� exceptino
     * @throws MessagingNetworkException
     */
    public void login() throws MessagingNetworkException {
        plugin.login(login, password, new String[]{}, MessagingNetwork.STATUS_ONLINE);
        plugin.addMessagingNetworkListener(new ICQGateMessagingNetworkListener(this));
    }

    /**
     * �������� ��� ������. ���� �� ������� - exception. ������ ����� ���� : online, offline, busy, unknown
     * @return ������ � ������� �������
     * @throws MessagingNetworkException
     */
    public String getStatus() throws MessagingNetworkException {
        int clientStatus = plugin.getClientStatus(login);
        switch (clientStatus){
            case MessagingNetwork.STATUS_ONLINE: return "online";
            case MessagingNetwork.STATUS_OFFLINE: return "offline";
            case MessagingNetwork.STATUS_BUSY: return "busy";
            default: return "UNKNOWN="+clientStatus;
        }
    }

    /**
     * ���������� ��������� ��������� ������������
     * @param msg ����� ���������
     * @param dstUin UIN ��������
     * @throws MessagingNetworkException
     */
    public void sendMessage(String msg, String dstUin) throws MessagingNetworkException {
        Boolean delivered = Boolean.TRUE;
        try{
            plugin.sendMessage(login, dstUin, msg);

        }
        catch(Exception e){
            delivered = Boolean.FALSE;
        }
        messages.add(new Message(new Date(), login, dstUin, msg, new Byte((byte) 1), delivered));
    }

    /**
     * ���������� ��������� ��������� ���������� � ������. ��������� ������� �� ������� Message
     * @return ��������� ���������
     */
    public List getMessages(){
        List m = messages;
        messages = new ArrayList();
        return m;
    }

    /**
     * �������� ������������
     * @throws MessagingNetworkException
     */
    public void logout() throws MessagingNetworkException {
        plugin.logout(login);
    }

    /**
     * ������������� ������ ���������
     * @param contactList ������ ������ ���������
     */
    public void setContactList(ContactList contactList) {
        this.contactList = contactList;
    }

    public ContactList getContactList(){
        return contactList;
    }

    protected void finalize() throws Throwable {
        try{
            if(plugin != null){
                plugin.logout(login);
            }
        }
        catch(Exception e){
            log.error("Cannot logout in finalizer",e);
        }
        super.finalize();
    }
}
