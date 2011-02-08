package ru.sns.core;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpTest {

    public static void main(String[] args) {
        ServerSocket ss = null;
        try{
            ss = new ServerSocket(8080);
            System.out.println("Start listening port "+8080);
            Socket s = ss.accept();
            System.out.println(s.getInetAddress());

            while(true){
                Thread.sleep(2000);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
            if(ss!=null)
                ss.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}
