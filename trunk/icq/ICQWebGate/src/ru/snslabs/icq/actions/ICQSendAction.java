package ru.snslabs.icq.actions;

import ru.snslabs.icq.ICQGate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ICQSendAction extends ICQAbstractAction {
    private static final Log log = LogFactory.getLog(ICQSendAction.class);

    public static final String TO_KEY = "to";
    public static final String MSG_KEY = "msg";

    /**
     * îòïğàâëÿåì ñîîáùåíèå
     * @param request
     * @param response
     * @param servletContext
     * @throws Exception
     */
    public void act(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {
        request.setCharacterEncoding("Cp1251");
        ICQGate icqGate = getICQGate(request, response);
        String to = request.getParameter(TO_KEY);
        String msg = new String(request.getParameter("msg").getBytes(),"UTF-8");
        log.info("sending message to : "+ to + "; msg:"+  msg);
        icqGate.sendMessage(msg, to);
        response.getWriter().print("OK");
        response.getWriter().flush();
    }

    /** Decoder */
    public static final String DECODE_STRING = "àáâãäå¸æçèéêëìíîïğñòóôõö÷øùúûüışÿÀÁÂÃÄÅ¨ÆÇÈÉÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞß%&";
    private String dec(String s){
        StringBuffer sb = new StringBuffer(s.length());
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) != '%'){
                sb.append(s.charAt(i));
            }
            else{
                sb.append( decsym( Integer.parseInt( s.substring(i+1,i+3) ) ) );
                i+=2;
            }
        }
        return sb.toString();
    }
    private char decsym(int code){
        if(code<DECODE_STRING.length()){
            return DECODE_STRING.charAt(code);
        }
        log.error("Wrong char code "+code);
        return '?';
    }
    public static void main(String[] args) {
        ICQSendAction icqSendAction = new ICQSendAction();
        System.out.println(icqSendAction.dec("this is %03%11%36--="));
    }

}
