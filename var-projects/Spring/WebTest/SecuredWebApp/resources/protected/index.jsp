<%@ page import="java.security.Principal"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>protected page</title></head>
  <body><h3>protected page</h3>
  <%
      Principal userPrincipal = request.getUserPrincipal();

      String userName = userPrincipal.getClass().toString() + " : "+ request.getRemoteUser();
      String userRole = "udefined";

  %>

  Username: <%= userName %><br>
  User role: <%= userRole %>
  </body>
</html>