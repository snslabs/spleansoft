package com.splean.web.file;

import java.net.MalformedURLException;
import java.net.URL;

class PathModel{
        /** server name */
        String server;
        /** username to login to ftp */
        String username;
        /** password to login to ftp */
        String password;
        /** ftp server port number */
        int port;
        /** path to parent directory (which contains desired file or folder) MUST endsWith("/") */
        String path;
        /** file or folder name. MUST NOT contain leading o trailing /'s*/
        String name;
        /** full path wich is actually path+name */
        String fullPath;

    public PathModel(String urlStirng) throws MalformedURLException {
            URL url = new URL(urlStirng);
            server = url.getHost();
            port = url.getPort()==-1?url.getDefaultPort():url.getPort();
            username = url.getUserInfo().substring(0,url.getUserInfo().indexOf(":"));
            password = url.getUserInfo().substring(url.getUserInfo().indexOf(":")+1);
            this.fullPath = url.getPath();
            path = url.getPath();
            if(path.contains("/")){
                path = path.substring(0,path.lastIndexOf("/")+1); 
            }
            name = url.getFile();
            if(name.endsWith("/")){
                name = name.substring(0, name.length()-1);
            }
            if(name.contains("/")){
                name = name.substring(name.lastIndexOf("/")+1);
            }
        }
    }
