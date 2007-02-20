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
<table border="0">
    <tr>
        <td>
            <img src="/images/open-dir.gif" alt="dir"/>&nbsp;
            <select id="drive" onchange="changeDrive()">
                <option value="C:/">C</option>
                <!--<option value="D:/">D</option>-->
                <!--<option value="E:/">E</option>-->
                <!--<option value="F:/">F</option>-->
            </select>
            <span id="dirSpan"></span>
        </td>
        <td>
            <span id="loadIndicator" style="display:none;">LOADING...</span>
        </td>
    </tr>
    <tr>
        <td>
            <a href="javascript:pasteFiles()"><img alt="paste" border="0" src="/images/paste.gif"/></a>
        </td>
    </tr>
</table>
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
                //                td.appendChild(createLink('javascript:cutFile("' + fullPath + '")', '/images/cut.gif'));
                td.appendChild(createLink('javascript:copyFile("' + fullPath + '")', '/images/copy.gif'));
                //                td.appendChild(createLink('javascript:pasteFile("' + fullPath + '")', '/images/paste.gif'));
                td.appendChild(createLink('javascript:deleteFile("' + fullPath + '")', '/images/delete.gif'));
                if (!options['rowData']['directory']) {
                    td.appendChild(createLink('javascript:download("' + fullPath + '")', '/images/download.gif'));
                }
            }
            return td;
        }
    }

            );
    showLoadInticator(false);
}

function createLink(_href, imgHref) {
    var a = document.createElement("a");
    a.href = _href;
    var img = document.createElement("img");
    img.src = imgHref;
    img.border = 0;
    a.appendChild(img);
    return a;
}
function navigate(dir) {
    changeDirectory(dir);
}

function changeDirectory(dirPath) {
    $("dirSpan").innerText = dirPath;
    $("currentDir").value = dirPath;
    showLoadInticator(true);
    FilesFacade.dir(dirPath, panelUpdated);
}
function changeDrive() {
    // alert(DWRUtil.getValue("drive"));
    changeDirectory(DWRUtil.getValue("drive"));
}
function download(filePath) {
    window.open("/download.do?path=" + filePath, "download", "menubar=0,resizable=0,width=350,height=250");
}
function deleteFile(filePath) {
    if (confirm("Deleting " + filePath)) {
        showLoadInticator(true);
        FilesFacade.deleteFile(filePath, fileDeleted)
    }
}
function fileDeleted(data) {
    if (data != null) {
        alert(data);
    }
    showLoadInticator(false);
    changeDirectory($("currentDir").value);
}
var clipboardId = null
function copyFile(path) {
    showLoadInticator(true);
    if (clipboardId == null) {
        alert('Clipboard not initialized!');
        return;
    }
    FilesFacade.copyFile(path, clipboardId, filesCopied)
}

function filesCopied(data) {
    if (data != null) {
        alert(data);
    }
    showLoadInticator(false);
    changeDirectory($("currentDir").value);
    showClipboard();
}

function pasteFiles() {
    FilesFacade.pasteFiles($("currentDir").value, clipboardId, filesCopied);
}
function initClipboard(data) {
    clipboardId = data;
}
function showLoadInticator(toShow) {
    $("loadIndicator").style.display = toShow?'':'none';
}
function showClipboard() {
    FilesFacade.getClipboard(clipboardId, clipboardUpdated);
}
function clipboardUpdated(res) {
    DWRUtil.removeAllRows("clipboardTable");
    DWRUtil.addRows("clipboardTable", res,
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
            return td;
        }
    }

            );
    showLoadInticator(false);
}

</script>
<br><br>
<html:form action="/upload" method="post" enctype="multipart/form-data">
    <html:hidden property="currentDir"/>
    <table class="panel" cellspacing="1">
        <tr>
            <th colspan="2">Upload</th>
        </tr>
        <tr>
            <td>
                <html:file property="uploadedFile"/>
            </td>
            <td>
                <html:submit value="Upload"/>
            </td>
        </tr>
    </table>
</html:form>
<script language="JavaScript">
    changeDirectory('c:/');
    FilesFacade.createClipboard(initClipboard);
    showClipboard();
</script>
</body>
</html>