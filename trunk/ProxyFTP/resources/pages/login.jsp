<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<jsp:useBean id="filebrowserConfig" class="com.splean.FileBrowserConfig" scope="application"/>
<html>
<head>
    <title>File Browser
        <bean:write name="filebrowserConfig" property="version"/>
    </title>
    <%@ include file="../lib/header.jsp" %>
</head>
<body>
<table width="100%" border="0">
    <tr>
        <td align="center">
            <html:form action="/loginSubimt">
                <table class="panel" cellspacing="1">
                    <tr> 
                        <th colspan="2">Login</th>
                    </tr>
                    <tr>
                        <td>
                            <table border="0">
                                <tr>
                                    <td>Username:</td>
                                    <td>
                                        <html:text property="username"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Password:</td>
                                    <td>
                                        <html:text property="password"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <html:submit style="width: 75px;"/>
                                        &nbsp;
                                        <html:reset style="width: 75px;"/>
                                    </td>
                                </tr>
                            </table>
                        </td>

                    </tr>
                </table>
            </html:form>
        </td>
    </tr>
</table>
</body>
</html>