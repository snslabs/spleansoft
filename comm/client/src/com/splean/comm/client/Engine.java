package com.splean.comm.client;

import com.splean.comm.common.Communications;
import com.splean.comm.common.CommunicationFactory;
import com.splean.comm.common.Subscriber;
import com.splean.comm.common.CommMessage;
import com.splean.comm.fake.FakeSubscriber;

import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Engine implements Runnable {
    /** местный пользователь */
    Subscriber from = new FakeSubscriber("ME!!","000-000-000","online");
    Subscriber to = new FakeSubscriber("fake user","111-111-111", "online");

    private long msgCheckPeriod = 5000; // период проверки новых сообщений - 5 сек.
    private Document msgLog;
    private Document msgLine;
    List messages;
    Communications comm = null;
    private boolean works = true;

    Engine() {
        comm = CommunicationFactory.getComm();

        msgLog = new PlainDocument();
        msgLine = new PlainDocument();
        messages = new ArrayList();

        // message listener
        Thread t = new Thread(this);
        t.start();
    }

    synchronized private void getMessage(CommMessage message){
        try{
            msgLog.insertString(msgLog.getLength(),message.getFrom().getName()+" says to "+
                    message.getTo().getName()+" at "+new Date(message.getTimestamp())+"\n"+
                    message.getMsgBody()+"\n",null);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendMsg(){
        try {
            String text = msgLine.getText(0, msgLine.getLength());
            sendMsg(text);
            msgLine.remove(0, msgLine.getLength());
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    synchronized  private void sendMsg(String msg){
        System.out.println("Sending message:"+msg);
        try {
            boolean result = comm.sendMessage(msg, to, from);
            if(result){
                msgLog.insertString(msgLog.getLength(),from.getName()+" says to "+to.getName()+"\n"+msg+"\n",null);
            }
            else{
                msgLog.insertString(msgLog.getLength(),"your message["+msg+"] cannot be delivered\n",null);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Document getMsgLog() {
        return msgLog;
    }

    public void setMsgLog(Document msgLog) {
        this.msgLog = msgLog;
    }

    public Document getMessageLineDocument() {
        return this.msgLine;
    }

    public void run() {
        while(works){
            CommMessage[] cm;
            try {
                Thread.sleep(this.msgCheckPeriod);
                cm = this.comm.checkMessages(this.from);
                if(cm != null && cm.length >0 ){
                    for (int i = 0; i < cm.length; i++) {
                        CommMessage commMessage = cm[i];
                        this.getMessage(commMessage);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                works = false;
            }
        }
    }

    public void stop(){
        this.works = false;
    }
}
