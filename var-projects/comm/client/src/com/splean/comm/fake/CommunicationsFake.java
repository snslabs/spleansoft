package com.splean.comm.fake;

import com.splean.comm.common.Communications;
import com.splean.comm.common.Subscriber;
import com.splean.comm.common.CommMessage;

import java.util.Date;

public class CommunicationsFake implements Communications {

    private final Subscriber fakeFrom = new FakeSubscriber("fake user","111-111-111", "online");

    public boolean sendMessage(String msgBody, Subscriber to, Subscriber from) throws Exception {
        return sendMessage(new CommMessage(from, to, msgBody, System.currentTimeMillis()));
    }

    public boolean sendMessage(CommMessage msg) throws Exception {
        System.out.println("Comm: sending message\n"+msg);
        return true;
    }

    public CommMessage[] checkMessages(Subscriber to) throws Exception {
        return new CommMessage[]{new CommMessage( fakeFrom, to, "fake message text "+new Date(),
            System.currentTimeMillis())};
    }

    public CommMessage[] checkMessages(Subscriber to, boolean clearFromServer) throws Exception {
        return checkMessages(to);
    }
}
