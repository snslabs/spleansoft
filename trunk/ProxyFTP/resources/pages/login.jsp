<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<jsp:useBean id="filebrowserConfig" class="com.splean.FileBrowserConfig" scope="application"/>
<html>
  <head>
      <title>File Browser <bean:write name="filebrowserConfig" property="version"/></title>
      <%@include file="../lib/header.jsp"%>
  </head>
  <body>
      <html:form  action="/loginSubimt" >
          Username: <html:text property="username"/><br>
          Password: <html:text property="password"/><br>
          <html:submit/><html:reset/>
      </html:form>
  </body>
</html>