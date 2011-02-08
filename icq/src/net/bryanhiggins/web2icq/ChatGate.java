// ChatGate.java
// Copyright (c) 2003-2006 by Bryan Higgins <bryan@bryanhiggins.net>.
// This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License.
// See LICENSE for details.

package net.bryanhiggins.web2icq;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** This servlet sets up and keeps track of applet sessions. */
public class ChatGate extends HttpServlet {
  
	private final String APPLETCODE = "net.bryanhiggins.web2icq.HTTPChatApplet.class";

    //parameters for the applet
    private String codeBase, servletPath;
    private String title, clientNick;

    //colors in rgb and hex
    private String bgColor, bgColorHex, color, colorHex;
    
    //points to the user sessions
    private Vector users; 

    /** Initialize variables */ 
    public void init(){
    	
    	//create empty vector to point at sessions
    	users = new Vector();

    	//open the configuration file
    	Properties config = new Properties();
        try{
            InputStream input = getServletContext().getResourceAsStream("/WEB-INF/web2icq.conf");
            config.load(input);
            input.close();
        }
        catch (Exception e){
            log("Error reading web2icq.conf");
        }

        //set variables from the configuration file
        codeBase = config.getProperty("codebase");
        servletPath = codeBase + "servlet/ICQMessageHandler";
        title = config.getProperty("title");
        clientNick = config.getProperty("clientnick");
        bgColor = config.getProperty("bgcolor");
        color = config.getProperty("color");

        //convert colors to hex
        bgColorHex = "";
        String temp;
        for (int i = 3; i < 10; i = i + 3){
        	temp = Integer.toHexString(new Integer(bgColor.substring(i - 3,i)).intValue());
        	if (temp.length() < 2){
        		temp = "0" + temp;
        	}
        	bgColorHex = bgColorHex + temp;
        }
        colorHex = "";
        for (int i = 3; i < 10; i = i + 3){
            temp = Integer.toHexString(new Integer(color.substring(i - 3,i)).intValue());
            if (temp.length() < 2){
                temp = "0" + temp;
            }
            colorHex = colorHex + temp;
        }

    }

    /**
     * Initializes a new applet session.
     *
     * @param req Should include the parameter 'name', the alias of the user.
     * @param res The page which invokes the applet or an error page if that alias already exists.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    	HttpSession session = req.getSession();
    	PrintWriter out = res.getWriter();
    	res.setContentType("text/html");
    	out.println("<HTML>");
    	out.println("<HEAD><TITLE>" + title + "</TITLE></HEAD>");
    	out.println("<BODY BGCOLOR='#" + bgColorHex + "' COLOR='#" + colorHex + "'><CENTER>");
    	if (!userExists(req.getParameter("name"))){
    		session.setAttribute("chat.pendingMessages",new Vector());
    		String name = req.getParameter("name");
    		name = name.replaceAll(" ","_");
    		session.setAttribute("chat.name",name);
    		users.add(session);
    		out.println("<APPLET CODE = '" + APPLETCODE +"' CODEBASE = '" + codeBase + "applets/" +
	                "' WIDTH=500 HEIGHT=200><PARAM NAME='user' VALUE='" + name + "'>" + 
                        "<PARAM NAME=title VALUE='" + title + "'><PARAM NAME=clientnick VALUE='" + clientNick + "'>" +
			"<PARAM NAME=bgcolor VALUE='" + bgColor + "'><PARAM NAME=color VALUE='" + color + "'>" + 
                        "<PARAM NAME=servletpath VALUE='" + servletPath + "'></APPLET>");
    	}
    	else{
    		out.println("<H2>Screen name taken or invalid. Please try again with a different name.</H2>");
        }
    	out.println("</CENTER></BODY></HTML>"); 
    }

    /** @return True if there is a user with 'name' already or if 'name' is a command */
    private boolean userExists(String name){
    	boolean exists = false;
    	String tempName;
        int i = 0;
        if (name.compareTo("r") == 0 || name.compareTo("l") == 0 || name.compareTo("") == 0) exists = true;
        while (i < users.size() && !exists){
        	tempName = null;
        	try{
        		tempName = (String)((HttpSession)users.elementAt(i)).getAttribute("chat.name");
        	}
        	catch (Exception ignored) {};
        	if (tempName == null){
        		users.removeElementAt(i);
        	}
        	else if (((String)((HttpSession)users.elementAt(i)).getAttribute("chat.name")).compareTo(name) == 0){
        		exists = true;
        	}
        	else{
        		i++;
        	}		    
        }
        return exists;
    }
}
