package spln.ejbtest;

import spln.ejb.SessionSFTestHome;
import spln.ejb.SessionSLTestHome;
import spln.ejb.SessionSFTest;
import spln.ejb.SessionSLTest;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        InitialContext ic = new InitialContext();
        Object ref = ic.lookup("ejb/SessionSFTestEJB");
        SessionSFTestHome sfHome = (SessionSFTestHome) PortableRemoteObject.narrow(ref, SessionSFTestHome.class);
        final SessionSFTest sessionSFTest2 = sfHome.create();
        System.out.println("Got EJB id="+sessionSFTest2.getBeanInstanceId());

        FileOutputStream fos = new FileOutputStream("ejb.tmp");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(sessionSFTest2);
        os.close();

        Thread.sleep(20000);

        System.out.println("Restoring ejb from file...");


        FileInputStream fis = new FileInputStream("ejb.tmp");
        ObjectInputStream is = new ObjectInputStream(fis);
        final SessionSFTest sessionSFTest1 = (SessionSFTest) is.readObject();
        System.out.println("Restored EJB with id = "+sessionSFTest1.getBeanInstanceId());
        System.out.println("Going on with tests...");
        for (int i = 0; i < 20; i++) {
            new Thread(
                    new Runnable(){
                        public void run() {
                            try{
                                System.out.println("Running 2 second method for bean "+sessionSFTest1.getBeanInstanceId());
                                final String str = sessionSFTest1.executeLongMethod(new Long(3000));
                                System.out.println("method finished for bean "+sessionSFTest1.getBeanInstanceId()+
                                " result is "+str);
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
            ).start();

            Thread.sleep(500);
        }


        System.out.println("Done testing statefull beans");

        System.out.println("Testing stateless bean...");
        ref = ic.lookup("ejb/SessionSLTestEJB");
        SessionSLTestHome slHome = (SessionSLTestHome) PortableRemoteObject.narrow(ref, SessionSLTestHome.class);
        for (int i = 0; i < 20; i++) {
            final SessionSLTest sessionSLTest1 = slHome.create();
            System.out.println(i + " : stateless bean ID is " + sessionSLTest1.getBeanInstanceId());
            new Thread(
                    new Runnable(){
                        public void run() {
                            try{
                                System.out.println("Running 2 second method for bean "+sessionSLTest1.getBeanInstanceId());
                                final String str = sessionSLTest1.executeLongMethod(new Long(1000));
                                System.out.println("method finished for bean "+sessionSLTest1.getBeanInstanceId()+
                                " result is "+str);
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
            ).start();
            Thread.sleep(100);
            new Thread(
                    new Runnable(){
                        public void run() {
                            try{
                                System.out.println("Running 2 second method for bean "+sessionSLTest1.getBeanInstanceId());
                                final String str = sessionSLTest1.executeLongMethod(new Long(1000));
                                System.out.println("method finished for bean "+sessionSLTest1.getBeanInstanceId()+
                                " result is "+str);
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
            ).start();
            Thread.sleep(500);
        }

    }
}
