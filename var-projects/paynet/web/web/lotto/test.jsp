<%@ page import="uz.sportloto.paynet.PaynetIntegrationFactory" %>
<%@ page import="uz.sportloto.DataSourceProvider" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Test payment</title></head>
  <body>
<%
    if(request.getParameter("ACT") != null){
        final String terminalId = request.getParameter("TERMINAL_ID");
        //noinspection UnhandledExceptionInJSP
        final String s = PaynetIntegrationFactory.createIntegration(terminalId, request.getParameterMap()).callPaynet();
        %>
<a href="test.jsp">BACK</a>
        <pre>
<%= s %>            
        </pre>
<a href="test.jsp">BACK</a>
<%

    }
    else{
%>
  <form method="get">
      ACT <input name="ACT" value="2"><BR>
      USERNAME <input name="USERNAME" value="sportlotto"><BR>
      PASSWORD <input name="PASSWORD" value="SportLotto"><BR>
      TERMINAL_ID <input name="TERMINAL_ID" value="3333"><BR>
      PAYNET_TERMINAL_ID <input name="PAYNET_TERMINAL_ID" value="9000000"><BR>
      INTERNAL_CHEQUE_ID <input name="INTERNAL_CHEQUE_ID" value="<%= System.currentTimeMillis() %>"><BR>
      SERVICE_ID <input name="SERVICE_ID" value="1"><BR>
      phone_number <input name="phone_number" value="1234567"><BR>
      summa <input name="summa" value="500"><BR>
<br>
      <input type="submit">
  </form>


<%
    }
%>
<hr>
  <%
      DataSourceProvider dsp = new DataSourceProvider();
      final DataSource source = dsp.getDataSource();
      if(source == null){
          out.println("Datasource not found!<br>");
      }else{
          final Connection connection = source.getConnection();
          if(connection == null){
              out.println("Cannot get connection <br>");
          }
          else{
              connection.close();
              out.println("Connection closed! all fine <br>");
          }
      }
  %>
  </body>
</html>
