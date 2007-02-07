package ru.snslabs.icq;

import org.jcq2k.MessagingNetworkListener;
import ru.snslabs.icq.model.Message;

import java.util.Date;

class ICQGateMessagingNetworkListener implements MessagingNetworkListener {

    private ICQGate icqGate;

    public ICQGateMessagingNetworkListener(ICQGate icqGate) {
        this.icqGate = icqGate;
    }

    public void messageReceived(byte networkId, String srcLoginId, String dstLoginId, String text) {
        System.out.println("message received!");
        icqGate.messages.add(new Message(new Date(), srcLoginId, dstLoginId, text, new Byte(networkId)));
    }

    public void contactsReceived(byte networkId, String srcLoginId, String dstLoginId, String[] contactsLoginIds, String[] contactsNicks) {
        // do nothing
    }

    public void statusChanged(byte networkId, String srcLoginId, String dstLoginId, int status, int reasonCategory, String reasonMessage) {
        // do nothing
    }
}
