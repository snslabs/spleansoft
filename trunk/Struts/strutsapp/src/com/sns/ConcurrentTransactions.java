package com.sns;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sns.model.User;

public class ConcurrentTransactions {
    public static final Log log = LogFactory.getLog("HU");

    public static void main(String[] args) {
        Thread t = new Thread(new FirstTX());
        t.start();

        Thread t2 = new Thread(new SecondTX());
        t2.start();


    }

    static class FirstTX implements Runnable{

        public void run() {
            Session s = HU.getSession();
            try{
                Transaction tx = HU.getTransaction();
                System.out.println("FT userlist: ");
                Hibernate1.printUsers("FT ");
                tx.commit();
                System.out.println("FT sleeping for 5 sec.");
                Thread.sleep(5000);
                tx = HU.getTransaction();
                System.out.println("FT userlist: ");
                Hibernate1.printUsers("FT ");
                tx.commit();
            }
            catch(HibernateException e){
                HU.getTransaction().rollback();
            }
            catch (InterruptedException e) {
                log.error(e);
            }
            finally{
                s.close();
            }
        }
    }

    static class SecondTX implements Runnable{

        public void run() {
            Session s = HU.getSession();
            try{
                Thread.sleep(500);
                Transaction tx = HU.getTransaction();
                System.out.println("ST userlist: ");
                Hibernate1.printUsers("ST : ");
                tx.commit();
                System.out.println("ST sleeping for 1 sec.");
                Thread.sleep(1000);
                Query q = s.createQuery("from User u where id=1");
                User u = (User) q.list().get(0);

                u.setName("username111");
                s.update(u);
                HU.getTransaction().commit();

                Thread.sleep(1000);
                tx = HU.getTransaction();
                System.out.println("ST userlist: ");
                Hibernate1.printUsers("ST : ");
                tx.commit();
            }
            catch(HibernateException e){
                HU.getTransaction().rollback();
            }
            catch (InterruptedException e) {
                log.error(e);
            }
            finally{
                s.close();
            }
        }
    }

    
}
