// ICQMessageHandler.java
// Copyright (c) 2003-2006 by Bryan Higgins <bryan@bryanhiggins.net>.
// This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License.
// See LICENSE for details.

package net.bryanhiggins.web2icq;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.jcq2k.*;
import org.jcq2k.icq2k.*;

/**
 * This servlet uses the jcq2k classes to send and recieve messages over the icq network.
 * POST requests have a 'message' parameter which is a message to be sent to the client.
 * GET responses are delayed until a new message arrives for that user.
 */
public class ICQMessageHandler extends HttpServlet implements MessagingNetworkListener{
  
    private String loginID;
    private String password;
    private String clientLoginID;
    private String[] contactList;
    private MessagingNetwork icqNetwork;
    private Vector users;
    private String mystatus;
    private String lastSentFrom;
    private MessageSource source = new MessageSource();
    
    /** Initialize instance variables and logon to the icq network. */
    public void init(){
	
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
    	loginID = config.getProperty("loginid");
    	password = config.getProperty("password");
    	clientLoginID = config.getProperty("clientloginid");
    	//clientNick = config.getProperty("clientnick");
    	contactList = new String[1];
    	contactList[0] = clientLoginID;

    	int status = 3;
    	users = new Vector();
        lastSentFrom = "";
        try{
        	icqNetwork = (MessagingNetwork) new ICQ2KMessagingNetwork();
        	icqNetwork.init();
        	icqNetwork.login(loginID, password, contactList, MessagingNetwork.STATUS_ONLINE);
        	icqNetwork.addMessagingNetworkListener(this);
        	status = icqNetwork.getStatus(loginID,clientLoginID);
        }
        catch (Exception e) {log("Initialization error: " + e.getMessage());}
        if (status == 1){
        	mystatus = "online";
        }
        else if (status == 2){
        	mystatus = "away";
        }
        else{
        	mystatus = "offline";
        }
    }

    /** Logout of the icq network */
    public void destroy(){
    	try{
    		icqNetwork.logout(loginID);
    	}
    	catch (Exception ignored) {}
    }

    /**
     * Sends a message over the icq network to the client. 
     *
     * @param req Includes parameter 'message', the text to be sent.
     * @param res None.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
    	HttpSession session = req.getSession();
    	String message = req.getParameter("message");
    	SendMessageThread smt = new SendMessageThread(icqNetwork, loginID, clientLoginID, message);
    	smt.start();
    	lastSentFrom = (String)session.getAttribute("chat.name");
    	res.setContentType("text/plain");
    	PrintWriter out = res.getWriter();
    	out.println("");
    }  

    /**
     * Redirects to status image or waits for new message to be added to the users session.
     *
     * @param image If this is not null, the response will be redirected to a status image. (For use on a website)
     * @param res A status image redirection or the new message string.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	HttpSession session;
    	SendMessageThread smt;
    	if (req.getParameter("expired") != null){
            //applet stopped
    		session = getSessionForName(req.getParameter("expired"));
    		session.setAttribute("chat.name","");
    		((Vector)session.getAttribute("chat.pendingMessages")).add("stopped");
    		getSessionForName("");
    		smt = new SendMessageThread(icqNetwork, loginID, clientLoginID, req.getParameter("expired") + " has signed off.");
    		smt.start();
            res.setContentType("text/plain");
            PrintWriter out = res.getWriter();
            out.println("");
        }
    	else if (req.getParameter("image") != null){
    		//redirect to status image
    		if (mystatus.compareTo("online") == 0){
    			res.sendRedirect("../images/online.png");
    		}
    		else if (mystatus.compareTo("away") == 0){
    			res.sendRedirect("../images/away.png");
    		}
    		else{
    			res.sendRedirect("../images/offline.png");
    		}
    	}
    	else{
    		session = req.getSession();
    		res.setContentType("text/plain");
    		PrintWriter out = res.getWriter();
    		try{
    			String name = (String)session.getAttribute("chat.name");
    			if (getSessionForName(name) == null){
    				//new user has signed on
    				users.addElement(session);
    				((Vector)session.getAttribute("chat.pendingMessages")).addElement(mystatus);
    				smt = new SendMessageThread(icqNetwork, loginID, clientLoginID, name + " has signed on.");
    				smt.start();
    			}
    			//wait for message
    			while(((Vector)session.getAttribute("chat.pendingMessages")).isEmpty()){	
    				new MessageSink().waitForMessage(source);
    			}
    			out.println(((Vector)session.getAttribute("chat.pendingMessages")).remove(0).toString());
    		}
    		catch (Exception e){
    			out.println(e.toString());
    		}
    	}
    }

    /** 
     * Adds messages recieved through the network to the appropriate users session.
     * Messages begining with "r " will be sent to the last person who sent a message.
     * The message "l" will send back a list of all screen names with valid sessions.
     * All other messages must begin with a valid screen name or an error message is sent back.
     */
    public void messageReceived(byte networkId, String srcLoginId, String dstLoginId, String text){
    	if (srcLoginId.compareTo(clientLoginID) == 0){
    		text = text.trim();
    		String username = text;
    		String message = "";
    		String errorMessage = null;
    		if (text.indexOf(" ") > 0){
    			username = text.substring(0,text.indexOf(" "));
    			message = text.substring(text.indexOf(" ") + 1);
    		}
    		if (username.compareTo("r") == 0){
    			username = lastSentFrom;
    		}
    		HttpSession session = getSessionForName(username);
    		if (username.compareTo("l") == 0){	    
    			if (users.size() > 0){
    				errorMessage = "Online users: ";
    				for (int i = 0; i < users.size(); i++){
    					errorMessage = errorMessage + ((HttpSession)users.elementAt(i)).getAttribute("chat.name") + "   ";
    				}
    			}
    			else {
    				errorMessage = "There are no users online.";
    			}
    		}
    		else if (session != null){
    			((Vector)session.getAttribute("chat.pendingMessages")).addElement(message);
    			source.sendMessage("boo");
    		}
    		else{
    			if (username == ""){
    				errorMessage = "You have not recieved any messages. Type 'l' for a list of all users online";
    			}
    			else{
    				errorMessage = username + " is not online. Type 'l' for a list of all users online.";
    			}
    		} 
    		if (errorMessage != null){
    			SendMessageThread smt = new SendMessageThread(icqNetwork, loginID, clientLoginID, errorMessage);
    			smt.start();
    		}
    	}
    }

    /** ignored */
    public void contactsReceived(byte networkId, String srcLoginId, String dstLoginId, String[] contactsLoginIds, String[] contactsNicks) {}

    /** Adds a status change message to all users' sessions. */
    public void statusChanged(byte networkId, String srcLoginId, String dstLoginId, int status, int reasonCategory, String reasonMessage) {
    	if (status == 1){
    		mystatus = "online";
    	}
    	else if (status == 2){
    		mystatus = "away";
    	}
    	else{
    		mystatus = "offline";
    	}
    	int i = 0;
    	while (i < users.size()){
    		try{
    			((Vector)((HttpSession)users.elementAt(i)).getAttribute("chat.pendingMessages")).addElement(mystatus);
    			i++;
    		}
    		catch (Exception e){
    			users.removeElementAt(i);
    		}
    	}
    }

    /**
     * @param name The screen name of the session to be found.
     * @return The session with the matching id or null if one does not exist.
     */
    public HttpSession getSessionForName(String name){
    	HttpSession session = null;
    	int i = 0;
    	while(i < users.size()){
    		try{
    			if (((String)((HttpSession)users.elementAt(i)).getAttribute("chat.name")).compareTo(name) == 0){
    				session =  (HttpSession)users.elementAt(i);
    			}
    		}
    		catch (Exception e){
    			users.removeElementAt(i);
    			i--;
    		}
    		i++;
    	}
    	return session;
    }

}

/** Thread to send messages over the ICQ network */
class SendMessageThread extends Thread {

    private MessagingNetwork network;
    private String loginID;
    private String clientLoginID;
    private String message;
    
    SendMessageThread(MessagingNetwork network, String loginID, String clientLoginID, String message){
    	super();
    	this.network = network;
    	this.loginID = loginID;
    	this.clientLoginID = clientLoginID;
    	this.message = message;
    	}

    	public void run(){
    		try{
    			network.sendMessage(loginID, clientLoginID, message);
    		}
    		catch (MessagingNetworkException ignored) {}
    	}
}

class MessageSink implements Observer{
	boolean message = false;
	synchronized public void update(Observable o, Object obj){
		message = true;
		notify();
	}
	synchronized public void waitForMessage(MessageSource source){
		source.addObserver(this);
		while (!message){
			try{wait();} catch(Exception e) { }
		}
		source.deleteObserver(this);
	}
}

class MessageSource extends Observable{
	public void sendMessage(String message){
		setChanged();
		notifyObservers(message);
	}
}




