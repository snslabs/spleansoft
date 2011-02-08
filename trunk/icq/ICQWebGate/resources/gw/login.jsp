<%@ page contentType="text/html;charset=Windows-1251" language="java" errorPage="../error.jsp" %>
<html>
<head>
    <title>Login to ICQ</title>
    <meta content="text/html; charset=Windows-1251" http-equiv="content-type"/>
</head>
<body>
<h3>Please enter your ICQ UIN and password</h3>
<form action="gwlogin.gw" method="post" name="jform">
    UIN:<input type="text" name="uin"><br>
    Password:<input type="password" name="password"><br>
    <input type="submit" value="login"><br>
</form>
<font color="red"><%= request.getAttribute("error") %></font>
<script>
    document.jform.uin.focus(); 
</script>
</body>
</html>