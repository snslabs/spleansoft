<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<jsp:useBean id="filebrowserConfig" class="com.splean.FileBrowserConfig" scope="application"/> 
<html>
  <head><title>File Browser <bean:write name="filebrowserConfig" property="version"/></title></head>
  <body style="font-family:verdana; font-size: x-small;">
    File browser proxy application<br>
    <logic:notPresent name="user">
        <b>Please login</b>
        <html:form focus="username" action="/logon" >
            Login:<html:text property="username"/><br>
            Password: <html:password property="password"/><br>
            <html:submit/><html:reset/>
        </html:form>
    </logic:notPresent>

  </body>
</html>