<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <title>Simple jsp page</title>
        <html:base/>
    </head>
    <body>
    <logic:present name="user" scope="session">
        <h3>Welcome
            <bean:write name="user" property="username"/>
        </h3>
    </logic:present>
    <logic:notPresent name="user" scope="session">
        <h3>Welcome World!!!</h3>
    </logic:notPresent>
    <html:errors/>
    <ul>
        <li>
            <html:link forward="logon">logon</html:link>
        </li>
        <li>
            <html:link forward="logoff">logoff</html:link>
        </li>
    </ul>
    </body>
</html>