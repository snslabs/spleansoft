        function pch(ch, oSpan) {
            if(oSpan==null){ alert("span is null"); }
            oSpan.innerHTML = oSpan.innerHTML + ch;
        }
        var str = null;
        var chIndex = 0;
        var oSpan = null;
        var intervalId = null;
        function printText(dstSpanId, srcSpanId) {
            chIndex = 0;
            str = document.getElementById(srcSpanId).innerHTML;
            oSpan = document.getElementById(dstSpanId);
            chIndex = 0;
            intervalId = window.setInterval(pct,1);
        }

        function printTextAjax(str_) {
            str = str_;
            chIndex = 0;
            intervalId = window.setInterval(pct,1);
        }

        function pct(){
            var ch = str.charAt(chIndex);
            if(chIndex > str.length){
                window.clearInterval(intervalId);
                window.status ='interval '+intervalId + " stopped."
                return;
            }
            if(ch=='<' ){
                var tagString = extractTag(str, chIndex);
                oSpan.innerHTML = oSpan.innerHTML + tagString;
                chIndex = chIndex + tagString.length -1
            }
            else if(ch=='&'){
                var symbolString = extractSymbol(str, chIndex);
                oSpan.innerHTML = oSpan.innerHTML + symbolString;
                chIndex = chIndex + symbolString.length -1
            }
            else{
                oSpan.innerHTML = oSpan.innerHTML + ch;
            }
            chIndex++;
        }

        function extractTag(str, startIndex){
            var str2 = str.substring(startIndex);
            var endIndex = str2.search("<\\/\\w>");
            endIndex = str2.indexOf(">",endIndex)+1+startIndex;
            return str.substring(startIndex, endIndex);
        }
        function extractSymbol(str, startIndex){
            var str2 = str.substring(startIndex);
            var endIndex = str2.indexOf(";")+startIndex+1;
            return str.substring(startIndex, endIndex);
        }

        function speed(millis){
            window.clearInterval(intervalId)
            intervalId = window.setInterval(pct,millis);
        }

        // AJAX functions
        var xmlReq = null;
        function getHTTPRequest(){
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
        function stateHandler(){
            if(xmlReq != null){
                if(xmlReq.readyState == 4){
                    var ss = xmlReq.responseText;
//                    document.getElementById("logSpan").innerText=ss;
                    // var ss = xml.getElementsByTagName("DATA")[0].nodeValue;
                    // alert(ss)
                    printTextAjax(ss);
                    showLoadingIndicator(false);

                }
            }
        }
        function loadPage(pageName){
            oSpan = document.getElementById('contentSpan');
            xmlReq = getHTTPRequest();
            if(xmlReq == null){
                alert("Cannot create XMLRequest");
            }
            showLoadingIndicator(true);
            xmlReq.onreadystatechange=stateHandler;
            xmlReq.open("GET", "pages/"+pageName+".html");
            xmlReq.send(null);
        }
        function showLoadingIndicator(toShow){
            document.getElementById('loaderIndicator').style.display = toShow?'':'none';
        }