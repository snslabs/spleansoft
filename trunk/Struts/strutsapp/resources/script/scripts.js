function pch(ch, oSpan) {
    if(oSpan==null){ alert("span is null"); }
    oSpan.innerHTML = oSpan.innerHTML + ch;
}
var str = null;
var chIndex = 0;
var oSpan = null;
var intervalId = null;
function printText(dstSpanId, srcSpanId) {
    str = document.getElementById(srcSpanId).innerHTML;
    oSpan = document.getElementById(dstSpanId);
    chIndex = 0;
    intervalId = window.setInterval(pct,1);
}

function pct(){
    var ch = str.charAt(chIndex);
    if(chIndex > str.length){
        window.clearInterval(intervalId);
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
//        alert(str.substring(startIndex, endIndex));
    return str.substring(startIndex, endIndex);
}

function fast(){
    window.clearInterval(intervalId)
    intervalId = window.setInterval(pct,1);
}

