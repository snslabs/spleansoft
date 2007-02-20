<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src='/dwr/interface/DWRExample.js' type="text/javascript"></script>
<script src='/dwr/interface/FilesFacade.js' type="text/javascript"></script>
<script src='/dwr/engine.js' type="text/javascript"></script>
<script src='/dwr/util.js' type="text/javascript"></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<style>
    body{
        font-family:verdana;
        font-size: x-small;
        color: <jsp:getProperty name="filebrowserConfig" property="defaultTextColor"/>;
        background-color: <jsp:getProperty name="filebrowserConfig" property="defaultBgColor"/>;
    }
    table{
        font-family:verdana;
        font-size: x-small;
        color: <jsp:getProperty name="filebrowserConfig" property="panelTextColor"/>;
        background-color: <jsp:getProperty name="filebrowserConfig" property="panelBgColor"/>;
    }
    TABLE.panel{
        background-color: black;
    }
    .panel TD{
        background-color:white;
    }
    .panel TH{
        background-color:white;
    }
    A {
        color: navy;
        font-weight: bold;
        text-decoration: none;
    }
</style>