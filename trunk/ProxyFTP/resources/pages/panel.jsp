<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<jsp:useBean id="filebrowserConfig" class="com.splean.FileBrowserConfig" scope="application"/>
<jsp:useBean id="panel" class="com.splean.web.model.FilePanel" scope="session"/>
<html>
<head>
    <title>File Browser
        <bean:write name="filebrowserConfig" property="version"/>
    </title>
    <%@ include file="../lib/header.jsp" %>
</head>
<body>
<b>Dir:</b><span id="dirSpan"></span>
<bean:write name="panel" property="path"/>
<br>
<table class="panel" cellspacing="1" cellpadding="1">
    <thead>
        <tr>
            <th width="16">&nbsp;</th>
            <th width="80">Name</th>
            <th width="30">Ext</th>
            <th width="52">Size</th>
            <th width="120">Date</th>
        </tr>
    </thead>
    <tbody id="panel1" class="panel">
    </tbody>
</table>
<script language="JavaScript">
    /*  // Provide a default path to dwr.engine
 if (dwr == null) var dwr = {};
 if (dwr.engine == null) dwr.engine = {};
 if (DWREngine == null) var DWREngine = dwr.engine;

 dwr.engine._defaultPath = '/dwr';

 if (FilesFacade == null) var FilesFacade = {};
 FilesFacade._path = '/dwr';
 FilesFacade.dir = function(p0, callback) {
     dwr.engine._execute(FilesFacade._path, 'FilesFacade', 'dir', p0, callback);
 }   */

    function panelUpdated(res) {
        DWRUtil.removeAllRows("panel1");
        DWRUtil.addRows("panel1", res,
                [
                        function(data) {
                            return " ";
                        },
                        function(data) {
                            return null; // data["name"];
                        },
                        function(data) {
                            return data["extension"];
                        },
                        function(data) {
                            return data["size"];
                        },
                        function(data) {
                            return data["date"];
                        }
                        ],
        {
            rowCreator:function(options) {
                return document.createElement("tr");
            },
            cellCreator:function(options) {
                var td = document.createElement("td");
                var data = options['rowData']['name'];
                var dirPath = options['rowData']['fullPath'];
                if(options['cellNum'] == 3){
                    td.align = "right";
                }
                if(options['cellNum'] == 1){
                    if(options['rowData']['directory']){
                        var a = document.createElement("a");
                        a.href = "javascript:navigate(\""+dirPath+"\")";
                        a.appendChild( document.createTextNode(data) );
                        td.appendChild(a);
                    }
                    else{
                        td.appendChild( document.createTextNode(data) )
                    }
                }
                return td;
            }
        }

                );

    }

    function navigate(dir){
        updatePanel(dir);
    }

    function updatePanel(dirPath) {
        $("dirSpan").innerText = dirPath;
        FilesFacade.dir(dirPath, panelUpdated);
    }

</script>
<button onclick="updatePanel('c:/Serge')">Refresh C:\</button>
</body>
</html>