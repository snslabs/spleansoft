<%@ page contentType="text/xml;charset=Windows-1251" language="java" %>
<%@ page import="java.util.Date"%>
<?xml version="1.0" encoding="Windows-1251"?>
<root xmlns:ig="http://www.snslabs.ru/icqgateway/msg_1_0.xsd">
    <messages>
        <message>
            <date><%= new Date()  %></date>
            <from>342367696</from>
            <fromnick>Splean</fromnick>
            <to>140486253</to>
            <text>Русское сообщение!!!</text>
        </message>
        <message>
            <date><%= new Date()  %></date>
            <from>342367696</from>
            <fromnick>Splean</fromnick>
            <to>140486253</to>
            <text>Ещё одно русское сообщение!!!</text>
        </message>
    </messages>
    <% System.out.println("Outputted successfully "); %>
</root>