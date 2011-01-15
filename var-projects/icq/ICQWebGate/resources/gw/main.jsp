<%@ page import="ru.snslabs.icq.ICQGate" %>
<%@ page import="ru.snslabs.icq.actions.ICQAbstractAction" %>
<%@ page import="ru.snslabs.icq.model.Contact" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.snslabs.icq.model.Message" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=Windows-1251" language="java" %>
<html>
<head>
    <title>Simple jsp page</title>
    <meta content="text/html; charset=Windows-1251" http-equiv="content-type"/>
    <link type="text/css" rel="stylesheet" href="../styles.css"/>
</head>

<body>
<%
    ICQGate icqGate = (ICQGate) session.getAttribute(ICQAbstractAction.ICQ_GATE_KEY);
    List log = icqGate.getMessages();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < log.size(); i++) {
        Message msg = (Message) log.get(i);
        sb.append(msg.getFormattedMessage()).append("\n");
    }
%>
<form name="jform" method="get" action="" onsubmit="{ sendMsg(); return false; }">
    Тут вобщем окошко с аськой, списком контактов и всё такое... :-)<br>

    <div style="font-family:verdana; font-size: 8pt; width:600px; height:400px; overflow-y:scroll; background: url('images/bg.jpg');"
         id="log"><%= sb.toString() %></div>
    <br>
    <select name="to">
        <%  Collection col = icqGate.getContactList().getContactsArray();
            for (java.util.Iterator it = col.iterator(); it.hasNext();) {
                Contact ca = (Contact) it.next();%>
        <option value="<%= ca.getUin() %>"><%= ca.getNick() %></option>
        <%  }   %>
    </select>
    <input name="msg" size="40">
    <input type="button" value="Send" onclick="sendMsg()">
</form>
<form name="addcontact" method="get" action="addcontact.gw">
    <input type="text" name="add_uin" size="12" maxlength="10">
    <input type="text" name="add_nick" size="12" maxlength="16">
    <input type="text" name="add_dsc" size="30">
    <input type="submit" value="add">
</form>
<br>

<div id="hourglass"></div>
<script language="javascript">
    window.setTimeout("getMsgs()", 3000);

    function getMsgs() {
        document.getElementById("hourglass").innerText = "Fetching messages..."
        var oReq;
        if (window.XMLHttpRequest) { // Mozilla, Safari, ...
            oReq = new XMLHttpRequest();
        }
        else if (window.ActiveXObject) { // IE
            oReq = new ActiveXObject("Microsoft.XMLHTTP");
        }
        oReq.onreadystatechange = function() {
            statchangehandler(oReq)
        };
        oReq.open('GET', "http://localhost:8888/gw/messages.jsp?random=" + Math.random(100), true);
        oReq.send(null);
    }
    function statchangehandler(oReq) {
        if (oReq.readyState !== 4) {
            return;
        }

        if (oReq.status == 200) {
            var oDoc = oReq.responseXML;
            // var aMsgs = oDoc.getElementsByTagName("ig:message");
            var from = "";
            var fromNick = "";
            var d = "";
            var text = "";
            var dir = "in";
            //            oo = document.createElement("pre");
            //            oo.innerText = oReq.responseText;
            //            document.getElementById("log").appendChild(oo);
            //            alert(oReq.responseText);
            oDoc.setProperty("SelectionLanguage", "XPath");
            var aMsgs = oDoc.documentElement.selectNodes("/root/messages/message");
            for (i = 0; i < aMsgs.length; i++) {
                d = aMsgs.item(i).childNodes.item(0).firstChild.nodeValue;
                from = aMsgs.item(i).childNodes.item(1).firstChild.nodeValue;
                fromNick = aMsgs.item(i).childNodes.item(2).firstChild.nodeValue;
                to = aMsgs.item(i).childNodes.item(3).firstChild.nodeValue;
                text = aMsgs.item(i).childNodes.item(4).firstChild.nodeValue;
                dir = aMsgs.item(i).childNodes.item(5).firstChild.nodeValue;
                addMessage(from, fromNick, d, text, dir);
            }
        }
        else {
            alert("Cannot get messages list: Error " + oReq.status);
        }
        document.getElementById("hourglass").innerText = "";
        window.setTimeout("getMsgs()", 3000);
    }
    function addMessage(from, fromNick, d, text, dir) {
        //        alert("fuck!!!");
        var oLog = document.getElementById("log");
        var oSpan = document.createElement("SPAN");
        if(dir=="in"){
            fromNick = "<a href='javascript:switchTo(\""+from+"\")'>"+fromNick+"</a>";
        }
        oSpan.innerHTML = "<img src='images/msg_" + dir + ".gif' border='0' width='16' height='16'> <b style='color: "
                + (dir == "in"?"red":"blue") + ";'>"+ fromNick+" (" + d + ") "
                + "</b><br>" + text + "<br><br>";
        oLog.appendChild(oSpan);
    }
</script>

<script>
    document.jform.msg.focus();
    function sendMsg() {
        var oReq;
        if (window.XMLHttpRequest) { // Mozilla, Safari, ...
            oReq = new XMLHttpRequest();
        }
        else if (window.ActiveXObject) { // IE
            oReq = new ActiveXObject("Microsoft.XMLHTTP");
        }
        var mms = document.jform.msg.value;
        oReq.open('POST', "http://localhost:8888/gw/gwsend.gw?to=" + document.jform.to.value, true);
        oReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=Windows-1251');
        oReq.send("msg="+mms);
        document.jform.msg.value = "";
        document.jform.msg.focus();
    }


        var enc_s = 'абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ%&';
        function enc(s) {
            var res = "";
            for(i=0;i<s.length;i++){
                res += encsym(s.charAt(i));
            }
            return res;
        }
        function encsym(s){
            var ord = enc_s.indexOf(s);
            if(ord!=-1){
                if(ord<10)
                    return '%'+0+""+ord;
                else
                    return '%'+ord;
            }
            return ""+s;
        }

    function switchTo(uin){
        document.jform.to.value=uin;
        document.jform.msg.focus();
    }

</script>
</body>


</html>