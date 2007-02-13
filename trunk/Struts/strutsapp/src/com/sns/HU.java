package com.sns;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HU {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static final ThreadLocal session = new ThreadLocal();
    private static final ThreadLocal transaction = new ThreadLocal();


    public static Session getSession(){
        Session s = (Session) session.get();
        if(s==null){
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s; 
    }

    public static Transaction getTransaction(){
        Transaction tx = (Transaction) transaction.get();
        if(tx==null || !tx.isActive()){
            if(tx!=null && !tx.isActive()){
                System.out.println("Transaction is inactive. creating new one.");
            }
            tx = getSession().beginTransaction();
            transaction.set(tx);
        }
        return tx;
    }


}
