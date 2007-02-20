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
<img src="/images/open-dir.gif" alt="dir"/>&nbsp;
<select id="drive" onchange="changeDrive()">
    <option value="C:/">C</option>
    <option value="D:/">D</option>
    <option value="E:/">E</option>
    <option value="F:/">F</option>
</select>
<span id="dirSpan"></span>
<bean:write name="panel" property="path"/>
<br><br>
<table border="0">
    <tr valign="top">
        <td>
            <table class="panel" cellspacing="1" cellpadding="2">
                <thead>
                    <tr>
                        <th width="16">&nbsp;</th>
                        <th width="80">Name</th>
                        <th width="30">Ext</th>
                        <th width="60">Size</th>
                        <th width="60">Actions</th>
                        <th width="120">Date</th>
                    </tr>
                </thead>
                <tbody id="panel1" class="panel">
                </tbody>
            </table>
        </td>
        <td>
            <table class="panel" cellspacing="1" cellpadding="2">
                <thead>
                    <tr>
                        <th width="16">&nbsp;</th>
                        <th width="80">Name</th>
                        <th width="30">Ext</th>
                        <th width="60">Size</th>
                    </tr>
                </thead>
                <tbody id="clipboardTable">
                </tbody>
            </table>
        </td>
    </tr>
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
                            return null;
                        },
                        function(data) {
                            return null;
                            // data["name"];
                        },
                        function(data) {
                            return data["extension"];
                        },
                        function(data) {
                            return data["size"];
                        },
                        function(data) {
                            return null;
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
                var fullPath = options['rowData']['fullPath'];
                if (options['cellNum'] == 0) {
                    if (options['rowData']['directory']) {
                        var img = document.createElement("img");
                        img.src = '/images/dir.gif';
                        //                        img.alt = 'folder pic';
                        td.appendChild(img);
                    }
                }
                else if (options['cellNum'] == 1) {
                    if (options['rowData']['directory']) {
                        var a = document.createElement("a");
                        a.href = "javascript:navigate(\"" + fullPath + "\")";
                        a.appendChild(document.createTextNode(data));
                        td.appendChild(a);
                    }
                    else {
                        td.appendChild(document.createTextNode(data))
                    }
                }
                else if (options['cellNum'] == 3) {
                    td.align = "right";
                }

                if ((options['cellNum'] == 4)) {
                    td.appendChild(createLink('javascript:cutFile("' + fullPath + '")', '/images/cut.gif'));
                    td.appendChild(createLink('javascript:copyFile("' + fullPath + '")', '/images/copy.gif'));
                    td.appendChild(createLink('javascript:pasteFile("' + fullPath + '")', '/images/paste.gif'));
                    td.appendChild(createLink('javascript:deleteFile("' + fullPath + '")', '/images/delete.gif'));
                    if (!options['rowData']['directory']) {
                        td.appendChild(createLink('javascript:download("' + fullPath + '")', '/images/download.gif'));
                    }
                }
                return td;
            }
        }

                );

    }

    function createLink(_href, imgHref){
        var a = document.createElement("a");
        a.href = _href;
        var img = document.createElement("img");
        img.src = imgHref;
        img.border = 0;
        a.appendChild(img);
        return a;
    }
    function navigate(dir) {
        updatePanel(dir);
    }

    function updatePanel(dirPath) {
        $("dirSpan").innerText = dirPath;
        FilesFacade.dir(dirPath, panelUpdated);
    }
    function changeDrive() {
        // alert(DWRUtil.getValue("drive"));
        updatePanel(DWRUtil.getValue("drive"));
    }
    function download(filePath){
        alert('downloading '+ filePath);
    }
    function deleteFile(filePath){
        alert('deleting '+filePath);
    }

</script>
<br><br>
<button onclick="updatePanel('c:/')">Refresh C:\</button><br><br>
<html:form action="/upload" method="post" enctype="multipart/form-data" >
    <html:hidden property="currentDir"/>
    <html:file property="uploadedFile"/><br>
    <html:submit/><html:reset/>
</html:form>
<script language="JavaScript">
    updatePanel('c:/');
</script>
</body>
</html>