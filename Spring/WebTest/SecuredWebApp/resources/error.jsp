<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ page import="java.util.Enumeration"%>
<html>
<head><title>Error</title></head>

<body>
<%
    int code;
    String msg;
    try {
        code = ((Integer)request.getAttribute("javax.servlet.error.status_code")).intValue();
        msg = (String) request.getAttribute("javax.servlet.error.message");
    }
    catch (Exception e) {
        e.printStackTrace();
        code = 500;
        msg="-";
    }

    if (code != 500) {
%>
<h2><%= code %>:<%= msg %></h2>
<%
    }
    else{
%>
<h2>Internal server error!!! : <%= msg %></h2>
<%
        Enumeration en = request.getAttributeNames();
        while(en.hasMoreElements()){
            String n = (String) en.nextElement();   %>
        <%= n %>:<%= request.getAttribute(n) %><br>
<%      }
    }
%>
</body>
</html>