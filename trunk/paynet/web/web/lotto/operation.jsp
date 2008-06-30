<%@ page import="oracle.jdbc.OracleConnection,
                 oracle.jdbc.OracleTypes,
                 oracle.sql.CLOB,
                 uz.sportloto.paynet.PaynetIntegrationFactory"
        %><%@ page import="java.io.BufferedOutputStream"
        %><%@ page import="java.io.OutputStream" %><%@ page import="java.io.OutputStreamWriter"
        %>
<%@ page import="java.util.zip.GZIPOutputStream"
        %><%@ page import="java.util.zip.ZipInputStream" %><%@ page import="javax.sql.DataSource"
        %><%@ page import="org.apache.commons.dbcp.DelegatingConnection" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="uz.sportloto.Configuration" %>
<%@ page import="java.sql.*" %>
<%!
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(Configuration.getReportDateFormat());
%>
<jsp:useBean id="datasourceProvider" class="uz.sportloto.DataSourceProvider" scope="application"
        /><%
    DataSource ods = datasourceProvider.getDataSource();
    StringBuffer outData = new StringBuffer();
    boolean compressOut = false;  // GZip output?

    int operCode;  // Operation code
    String param;  // Parameters
    int terminalId = -1;
    int agentId = -1;

    Connection connection = null;
    OracleConnection conn = null;
    CallableStatement cs = null;
    ResultSet resSet = null;

    try {
        response.setContentType("text/html; charset=UTF-8");
        if (request.getParameter("TEST") != null) {
            out.print("Test succeeded! " + new java.util.Date());
            out.flush();
            return;
        }
        // Get parameters
        operCode = request.getIntHeader("OPER");
        param = request.getHeader("PARAM");
        System.out.println("\tOPER  = " + operCode);
        System.out.println("\tPARAM = " + param);
        {
            String code = request.getHeader("TERMINAL");
            System.out.println("\tTERMINAL = " + code);
            if (code != null) {
                int loc = code.indexOf(';');
                if (loc == -1)
                    terminalId = Integer.parseInt(code);
                else {
                    terminalId = Integer.parseInt(code.substring(0, loc));
                    agentId = Integer.parseInt(code.substring(loc + 1));
                }
            }
        }
// Test!
        if (operCode == -1) {
            operCode = 3;
            param = "1";
            terminalId = -1;
            agentId = -1;
        }
//
        if("PROVIDERS".equals(request.getParameter("check"))){
            operCode = 8;
        }
        // Connect to database
        connection = ods.getConnection();
        if(connection instanceof DelegatingConnection){
            conn = (OracleConnection) ((DelegatingConnection)connection).getInnermostDelegate();
        }
        else{
            conn = (OracleConnection) connection;
        }
        conn.setAutoCommit(false);
        System.out.println("\t >> OPER CODE = " + operCode);
        switch (operCode) {
            //---------------------------------------------------------------------
            case 1:    // �������� ���������
            {
                // Get uploaded file
                int len = request.getContentLength();
                if (len == -1)
                    throw new Exception("EMPTY CONTENT");

                ServletInputStream is = request.getInputStream();
                final int bufsize = 64 * 1024;
                byte buf[] = new byte[bufsize];
                int nr;  // number of readed bytes

                // Skip headers
                {
                    // Get length of Id of octet stream
                    nr = is.readLine(buf, 0, bufsize);
                    if (nr > 0)
                        len -= 2 * nr + 4;  // "--\r\n"
                    else
                        len = -1;
                    // Get headers
                    while (len > 0) {
                        nr = is.readLine(buf, 0, bufsize);
                        if (nr == -1) {
                            len = -1;
                            break;
                        }
                        len -= nr;
                        if (nr < 3)  // empty line
                            break;
                    }
                }

                // Get content
                CLOB inData = CLOB.createTemporary(conn, true, CLOB.DURATION_SESSION);
                {
                    OutputStream lobStream = inData.getAsciiOutputStream();
                    ZipInputStream zin = new ZipInputStream(is);

                    zin.getNextEntry();
                    while (zin.available() == 1) {
                        nr = zin.read(buf, 0, bufsize);
                        if (nr == -1)
                            break;
                        lobStream.write(buf, 0, nr);
                    }
                    zin.closeEntry();
                    zin.close();
                    lobStream.close();
                }

                // Call the Procedure
                int res = 0;
                if (inData.length() == 0)
                    throw new Exception("INVALID CONTENT");
                else {
                    cs = conn.prepareCall("Begin ? := RECV_FILE.Save_Clob( ?, ?, ?, ?); End;");

                    cs.registerOutParameter(1, Types.NUMERIC);
                    cs.setInt(2, terminalId);
                    cs.setInt(3, agentId);
                    cs.setObject(4, inData);
                    cs.registerOutParameter(5, Types.VARCHAR);
                    cs.execute();
                    res = cs.getInt(1);
                }

                response.setIntHeader("OPER", res);
                response.setHeader("DATE_CHAR", cs.getString(5));
                if (res == 0)
                    outData.append("Error");
                break;
            }
            //---------------------------------------------------------------------
            case 2:    // ���������� ������������ �����������
            {
                String modules = request.getParameter("MODULES");
                int res;

                cs = conn.prepareCall("Begin ? := SEND_FILE.Is_Soft_Upgrade( ?, ?, ?, ?, ?); End;");

                cs.registerOutParameter(1, Types.NUMERIC);
                cs.setInt(2, terminalId);
                cs.setString(3, modules);
                cs.registerOutParameter(4, Types.VARCHAR);
                cs.registerOutParameter(5, Types.VARCHAR);
                cs.registerOutParameter(6, Types.VARCHAR);
                cs.execute();
                res = cs.getInt(1);

                response.setIntHeader("OPER", res);
                outData.append((res == 1) ? (cs.getString(4) + "\n" + cs.getString(5)) : cs.getString(6));
                break;
            }
            //---------------------------------------------------------------------
            case 3:    // ��������� ��������� ����� � �� �������
            {
                String dateLastLoaded = request.getParameter("DATE_LOADED");
                int res;

                cs = conn.prepareCall("Begin ? := SEND_FILE.Create_Draws_Setup( ?, ?, ?, ?); End;");

                cs.registerOutParameter(1, Types.NUMERIC);
                cs.setInt(2, terminalId);
                cs.setString(3, dateLastLoaded);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.registerOutParameter(5, Types.VARCHAR);
                cs.execute();
                res = cs.getInt(1);

                response.setIntHeader("OPER", res);
                if (res == 1) {
                    resSet = (ResultSet) cs.getObject(4);
                    while (resSet.next()) {
                        outData.append(resSet.getString(1));
                        outData.append("\n");
                    }
                } else
                    outData.append(cs.getString(5));

                compressOut = true;
                break;
            }
            //---------------------------------------------------------------------
            case 4:    // ���������� �������
            {
                String dateLastLoaded = request.getParameter("DATE_LOADED");
                int res;

                cs = conn.prepareCall("Begin ? := SEND_FILE.Create_Win_Table( ?, ?, ?, ?); End;");

                cs.registerOutParameter(1, Types.NUMERIC);
                cs.setInt(2, terminalId);
                cs.setString(3, dateLastLoaded);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.registerOutParameter(5, Types.VARCHAR);
                cs.execute();
                res = cs.getInt(1);

                response.setIntHeader("OPER", res);
                if (res == 1) {
                    resSet = (ResultSet) cs.getObject(4);
                    while (resSet.next()) {
                        outData.append(resSet.getString(1));
                        outData.append("\n");
                    }
                } else
                    outData.append(cs.getString(5));

                compressOut = true;
                break;
            }
            //---------------------------------------------------------------------
            case 5:    // ������
            {
                int reportCode = Integer.parseInt(request.getParameter("REPORT_CODE"));
                int drawId = -1;
                Date reportDate = null;
                {
                    String s = request.getParameter("DRAW_ID");
                    if (s != null)
                        drawId = Integer.parseInt(s);
                    s = request.getParameter("REPORT_DATE");
                    if (s != null){
                        try{
                            reportDate = SIMPLE_DATE_FORMAT.parse(s);
                        }
                        catch(Exception e){
                            reportDate = null;
                        }
                    }

                }
                int res;

                cs = conn.prepareCall("Begin ? := SEND_FILE.Report(?, ?, ?, ?, ?, ?, ?); End;");

                cs.registerOutParameter(1, Types.NUMERIC);
                cs.setInt(2, terminalId);
                cs.setLong(3,agentId);
                if(reportDate != null){
                    cs.setTimestamp(4, new Timestamp(reportDate.getTime()));
                }
                else{
                    cs.setNull(4,Types.TIMESTAMP);
                }
                cs.setInt(5, reportCode);
                cs.setInt(6, drawId);
                cs.registerOutParameter(7, OracleTypes.CURSOR);
                cs.registerOutParameter(8, Types.VARCHAR);
                cs.execute();
                res = cs.getInt(1);

                response.setIntHeader("OPER", res);
                if (res == 1) {
                    resSet = (ResultSet) cs.getObject(7);
                    while (resSet.next()) {
                        outData.append(resSet.getString(1));
                        outData.append("\n");
                    }
                } else
                    outData.append(cs.getString(8));

                compressOut = true;
                break;
            }
            case 18:    // взаимодействие с PAYNET
            {
                int act = Integer.parseInt(request.getParameter("ACT"));
                String paynetTerminalID = request.getParameter("TERMINAL_ID");
                switch(act){
                    case 2:
                    case 3:
                    case 4:{
                        System.out.println("Preparing paying to paynet");
                        PaynetIntegrationFactory.createIntegration(paynetTerminalID, request.getParameterMap());
                        String result = PaynetIntegrationFactory.createIntegration(paynetTerminalID, request.getParameterMap()).callPaynet();
                        outData.append(result);
                        break;
                    }
                    default:{
                        outData.append("Invalid paynet code!!!\n");
                    }
                }
                break;
            }
            case 8:    // получение списка провайдеров
            {
                System.out.println("Getting list of providers=" + terminalId);
                try{
                    String paynetTerminalID = request.getParameter("TERMINAL_ID");
                    String paynetProviders = PaynetIntegrationFactory.createIntegration(paynetTerminalID, request.getParameterMap()).callPaynet();
                    System.out.println("Paynet providers content:\n"+paynetProviders+"\n-- end of paynet providers");
                    response.setIntHeader("OPER", 8);
                    outData.append(paynetProviders);
                    compressOut = true;
                }
                catch(Exception e){
                    outData.append(e.getMessage());
                    e.printStackTrace();
                }
                break;
            }
            //---------------------------------------------------------------------
            case 9:    // ��������� ��� ���������
            {
                System.out.println("Getting message for terminalId=" + terminalId);
                cs = conn.prepareCall("Begin ? := SEND_FILE.Message( ?, ?); End;");

                cs.registerOutParameter(1, Types.NUMERIC);
                cs.setInt(2, terminalId);
                cs.registerOutParameter(3, Types.VARCHAR);
                cs.execute();
                response.setIntHeader("OPER", cs.getInt(1));
                outData.append(cs.getString(3));

                compressOut = true;
                break;
            }
            //---------------------------------------------------------------------
            default:
                throw new Exception("INVALID OPERATION CODE");
        }

        // Output data
        {
            try{
            OutputStreamWriter zout;

            OutputStream os = response.getOutputStream();
            if (compressOut) {
                response.setHeader("Content-Encoding", "gzip");
                os = new GZIPOutputStream(os);
            }
            zout = new OutputStreamWriter(new BufferedOutputStream(os), "UTF-8");
            zout.write(outData.toString());
            zout.close();
            }
            catch(Exception e){
                System.out.println("Cannot out file...");
                e.printStackTrace();
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
        response.setIntHeader("OPER", 0);
//        out.print(e.toString());
    } finally {
        if (cs != null) cs.close();
        if (resSet != null) resSet.close();
        if (connection != null) connection.close();
        System.out.println("------------------------------------------------- end request -");
    }
%>
