<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
  <h4>Please logon:</h4>
  <form action="j_security_check" method="POST">
      <input type="text" name="j_username"><br>
      <input type="password" name="j_password"><br>
      <input type="submit" value="Login">
  </form>

  </body>
</html>