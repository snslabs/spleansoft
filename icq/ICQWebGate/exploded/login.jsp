<%@ page contentType="text/html;charset=Windows-1251" language="java" errorPage="error.jsp" %>
<html>
  <head>
      <title>Login to ICQGate system</title>
      <meta content="text/html; charset=Windows-1251" http-equiv="content-type"/>
  </head>
  <body>
  <h3>Login to ICQGate</h3>
  <form method="post" action="login.gw" name="jform">
      <input type="text" name="username"><br>
      <input type="password" name="password"><br>
      <input type="submit" value="login"><br>
  </form>
  <font color="red"><%= request.getAttribute("error") %></font>
  <script>
      document.jform.username.focus();
  </script>
  </body>
</html>