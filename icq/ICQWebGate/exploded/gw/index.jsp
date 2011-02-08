<%@ page contentType="text/html;charset=Windows-1251" language="java" %>
<html>
<head>
    <title>Simple jsp page</title>
    <meta content="text/html; charset=Windows-1251" http-equiv="content-type"/>
</head>

<body>
<jsp:useBean id="userModel" scope="session" class="ru.snslabs.icq.model.UserModel">
</jsp:useBean>

You are authorized in system.
Your login is:
<jsp:getProperty name="userModel" property="username"/>
Your role is:
<jsp:getProperty name="userModel" property="role"/>
<br>
Please login to ICQ to send/receive messages.<br>
<a href="/gw/login.jsp">Login</a>
</body>
</html>