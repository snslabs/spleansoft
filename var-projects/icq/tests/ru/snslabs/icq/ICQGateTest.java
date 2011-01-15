package ru.snslabs.icq;

import junit.framework.TestCase;

import java.util.List;

import ru.snslabs.icq.model.Message;

public class ICQGateTest extends TestCase {
    /*
    public void testLoginAndSend() throws Exception{
        ICQGate icqGate = new ICQGate("140486253", "splean");
        icqGate.login();
        Thread.sleep(5000);
        icqGate.sendMessage("test message","342367696");
        Thread.sleep(5000);
        icqGate.logout();
    }
            */
    public void testRecieveMessages() throws Exception{
        ICQGate icqGate = new ICQGate("140486253", "splean");
        icqGate.login();
        icqGate.sendMessage("please send me a message. I'll wait 10 second for it.","342367696");
        Thread.sleep(10000);
        List l = icqGate.getMessages();
        if(l!=null && !l.isEmpty()){
            for (int i = 0; i < l.size(); i++) {
                Message o = (Message) l.get(i);
                System.out.println(i+". "+o.toString());
            }
        }
        Thread.sleep(5000);
        icqGate.logout();

    }
}