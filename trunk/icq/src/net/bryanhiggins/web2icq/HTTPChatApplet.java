// HTTPChatApplet.java
// Copyright (c) 2003-2006 by Bryan Higgins <bryan@bryanhiggins.net>.
// This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License.
// See LICENCE for details.

package net.bryanhiggins.web2icq;

import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This applet is a simple chat interface that uses HTTP to communicate with a servlet.
 * Clicking send or pressing enter on the input field sends the message to the servlet using POST.
 * To recieve messages, the applet sends a GET request to the servlet which must block until a message is sent.
 * 
 */
public class HTTPChatApplet extends Applet implements Runnable {
  
	private String servletPath;
    private String title;
    private String clientNick;
    private Color color;
    private Color bgColor;

    private Panel topPanel;
    private Panel inputPanel;
    private Label titleLabel;
    private Label statusLabel;
    private Label queryLabel;
    private TextArea text;
    private TextField input;
    private Button send;
  
    private Thread thread;
    private InputStream inputStream;
    private String user;

    /** Initializes the instance variables and creates the UI */ 
    public void init(){
	
    	//set variables from the parameters
    	servletPath = getParameter("servletpath");
    	title = getParameter("title");
    	clientNick = getParameter("clientnick");
    	color = new Color(new Integer(getParameter("color").substring(0,3)).intValue(), 
			  new Integer(getParameter("color").substring(3,6)).intValue(),
			  new Integer(getParameter("color").substring(6,9)).intValue());
    	bgColor = new Color(new Integer(getParameter("bgcolor").substring(0,3)).intValue(),
			    new Integer(getParameter("bgcolor").substring(3,6)).intValue(),
			    new Integer(getParameter("bgcolor").substring(6,9)).intValue());
    	user = getParameter("user");
    
    	//create top labels 
    	titleLabel = new Label(title);
        titleLabel.setBackground(bgColor);
        titleLabel.setForeground(color);
        statusLabel = new Label("status:         ");
        statusLabel.setBackground(bgColor);
        statusLabel.setForeground(color);
        topPanel = new Panel(new BorderLayout());
        topPanel.setBackground(bgColor);
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(statusLabel, BorderLayout.EAST);

        //create text panel
        text = new TextArea("",40,80,TextArea.SCROLLBARS_VERTICAL_ONLY);
        text.setEditable(false);
        text.setBackground(bgColor);
        text.setForeground(color);

        //create input panel
        queryLabel = new Label("Message: ");
        queryLabel.setBackground(bgColor);
        queryLabel.setForeground(color);
        input = new TextField();
        input.setBackground(bgColor);
        input.setForeground(color);
        send = new Button("Send");
        inputPanel = new Panel(new BorderLayout());
        inputPanel.setBackground(bgColor);
        inputPanel.add("West",queryLabel);
        inputPanel.add("Center",input);
        inputPanel.add("East",send);

        //add the components to the applet
        setLayout(new BorderLayout());
        add("North",topPanel);
        add("Center",text);
        add("South",inputPanel);
    }
   
    /** Starts the thread */
    public void start(){
    	thread = new Thread(this);
    	thread.start();
    }

    /** Sends an expired message */
    public void stop(){
    	thread = null;
    	try{
    		inputStream.close();
    		URL url = new URL(getCodeBase(), servletPath + "?expired=" + URLEncoder.encode(user,"UTF-8"));
    		URLConnection connection = url.openConnection();
    		connection.setUseCaches(false);
    		InputStream in = connection.getInputStream();
    		in.close();
    	}
    	catch (Exception e){
    		System.out.println("Unexpected error: " + e.getClass().getName() + ": " + e.getMessage());
    	}
    }

    /**
     * Checks for new messages until "expired" is recieved.
     * Changes the status if a status change message is recieved. 
     * Puts recieved messages into the text area.
     */
    public void run(){
    	
    	String message;
    	boolean expired = false;
    	boolean stopped = false;
    	
    	while(!expired){
    		message = getMessage();
    		if (message.compareTo("expired") == 0){
    			expired = true;
    		}
    		else if (message.compareTo("stopped") == 0){
    			expired = true;
    			stopped = true;
    		}
    		else if (message.compareTo("online") == 0){
    			statusLabel.setText("status: online");
    		}
    		else if (message.compareTo("offline") == 0){
    			statusLabel.setText("status: offline");
    		}
    		else if (message.compareTo("away") == 0){
    			statusLabel.setText("status: away");
    		}
    		else{
    			text.append(message);
    		}
    	}
    	if (!stopped){
    		input.setEnabled(false);
    		send.setEnabled(false);
    		statusLabel.setText("");
    		text.append("");
    		text.append("***Session Expired. Open a new window to start a new chat session.***");
    		this.stop();
    	}
    }

    /** Sends the text in the input area then clears the input area and adds the sent message to the text area.*/
     public boolean handleEvent(Event event){
        if (event.id == Event.ACTION_EVENT){
        		String message = input.getText();
        		input.setEnabled(false);
        		send.setEnabled(false);
        		sendMessage(user + ": " + message);
        		input.setEnabled(true);
        		send.setEnabled(true);
        		input.setText("");
        		text.append(user +  getTimeStamp() + " : " + message + "\n");
        		return true;
        }
        return false;
    }

    /**
     * Sends a GET request to the servlet. The servlet must block until a message is available.
     *
     * @return Message to be posted or one of the status messages (online/offline/away)
     */
    private String getMessage(){
    	
    	//send GET request and get response
    	String message = null;
    	while (message == null){
    		try{
    			URL url = new URL(getCodeBase(), servletPath);
    			URLConnection connection = url.openConnection();
                connection.setUseCaches(true);
                inputStream = connection.getInputStream();
                BufferedReader dataInput = new BufferedReader(new InputStreamReader(inputStream));
                message = dataInput.readLine();
                inputStream.close();
    		}
    		catch (Exception e){
    			System.out.println("Unexpected Exception: " + e.getClass().getName() + ": " + e.getMessage());
    		} 
    	}

    	//add alias and timestamp if the message is not a status change
    	if (message.compareTo("expired") == 0 || message.compareTo("online") == 0 || 
    			message.compareTo("offline") == 0 || message.compareTo("away") == 0){
    		return message;
    	}
    	else{
    		return clientNick + getTimeStamp() + " : " + message + "\n";
    	}
    }

    /** Sends a message to the servlet through POST.
     * 
     * @param message The text of the message to be sent.
     * @return Response from the server.
     */
    private void sendMessage(String message) {
    	try{
    		URL url = new URL(getCodeBase(), servletPath);
    		URLConnection connection = url.openConnection();
    		connection.setDoOutput(true);
    		connection.setDoInput(true);
    		connection.setUseCaches(true);
    		DataOutputStream output = new DataOutputStream(connection.getOutputStream());
    		output.writeBytes(URLEncoder.encode("message","UTF-8") + "=" + URLEncoder.encode(message,"UTF-8"));
    		output.flush();
    		output.close();
    		InputStream input = connection.getInputStream();
    		input.close(); 
    	}
    	catch (Exception e){
    		System.out.println("Exception: " + e.getClass().getName() + ": " + e.getMessage());
    	} 
    }

    /** @return The current time in the format [HH:MM:SS] */
    private String getTimeStamp(){
    	
    	Calendar timestamp = Calendar.getInstance();
    	String timestampHour, timestampMinute, timestampSecond;
    	
    	if (timestamp.get(Calendar.HOUR_OF_DAY) >= 10){
    		timestampHour = "" + timestamp.get(Calendar.HOUR_OF_DAY);
    	}
    	else{
    		timestampHour = "0" + timestamp.get(Calendar.HOUR_OF_DAY);
    	}  
    	if (timestamp.get(Calendar.MINUTE) >= 10){
    		timestampMinute = "" + timestamp.get(Calendar.MINUTE);
    	}
    	else{
    		timestampMinute = "0" + timestamp.get(Calendar.MINUTE);
    	}
    	if (timestamp.get(Calendar.SECOND) >= 10){
    		timestampSecond = "" + timestamp.get(Calendar.SECOND);
    	}
    	else{
    		timestampSecond = "0" + timestamp.get(Calendar.SECOND);
    	}
    	return " [" + timestampHour + ":" + timestampMinute + ":" + timestampSecond + "]";
    }

}


