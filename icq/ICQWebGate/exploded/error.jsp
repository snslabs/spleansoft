<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=Windows-1251" language="java" isErrorPage="true" %>
<html>
  <head>
      <title>ICQGate v 1.0.</title>
      <meta content="text/html; charset=Windows-1251" http-equiv="content-type"/>
  </head>
  <body>
    <h3>Error!!!</h3>
<%
    Throwable t = (Throwable) request.getAttribute("exception");
    if(t!=null){
        out.println(t+"<hr>");
        t.printStackTrace();
    }
    List errors = (List) request.getAttribute("errors");
    if(errors != null){
        for (int i = 0; i < errors.size(); i++) {
            Object o =  errors.get(i);
            out.println(o+"<br>");
        }
    }

%>
  </body>
</html>