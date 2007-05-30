<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
  <head><title>Logon</title></head>
  <body>
  <html:errors/>
    <h3>Please logon</h3>
  <html:form action="LogonSubmit" focus="username">
      <table border="0">
          <tr>
              <th>Username:</th>
              <td><html:text property="username"/></td>
          </tr>
          <tr>
              <th>Password:</th>
              <td><html:text property="password"/></td>
          </tr>
      </table>
      <html:submit/> <html:reset/>
  </html:form>
  </body>
</html>