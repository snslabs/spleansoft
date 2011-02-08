<%@ page contentType="text/xml;charset=Windows-1251" language="java" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="ru.snslabs.icq.ICQGate"%>
<%@ page import="ru.snslabs.icq.actions.ICQAbstractAction"%>
<%@ page import="java.util.List"%>
<%@ page import="ru.snslabs.icq.model.Message"%>
<?xml version="1.0" encoding="Windows-1251"?>
<%!
    private static final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
%><root xmlns:ig="http://www.snslabs.ru/icqgateway/msg_1_0.xsd">
    <messages>
<%
    ICQGate icqGate = (ICQGate) session.getAttribute(ICQAbstractAction.ICQ_GATE_KEY);
    List l = icqGate.getMessages();
    for(int i = 0; i < l.size(); i++){
        Message msg = (Message) l.get(i);
        System.out.println(msg.getFormattedMessage());
        String txt = msg.getText();
        txt = txt.replaceAll(":\\-\\)","<img src='smiles/ab.gif'>");
%>
        <message>
            <date><%= sdf.format(msg.getDate())  %></date>
            <from><%= msg.getFrom() %></from>
            <fromnick><%= icqGate.getContactList().getNickName(msg.getFrom()) %></fromnick>
            <to><%= msg.getTo() %></to>
            <text><![CDATA[<%= txt %>]]></text>
            <dir><%= msg.getTo().equals(icqGate.getLogin())?"in":"out" %></dir>
        </message>
<%  }   %>
    </messages>
</root>