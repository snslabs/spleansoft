<%@ page import="java.util.Enumeration"%>
<%--
  Created by IntelliJ IDEA.
  User: slubimov
  Date: 02.10.2006
  Time: 18:55:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
  <h3>ERROR</h3>
  Here is the content of request attributes:
  <%
      System.out.println("result.jsp processed");
      Enumeration attributeNames = request.getAttributeNames();
      while(attributeNames.hasMoreElements()){
          String name = (String) attributeNames.nextElement();  %>
        <%= name %>: <%= request.getAttribute(name) %><br/>
<%      }   %>

  </body>
</html>