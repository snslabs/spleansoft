package com.sns;

import com.sns.model.Phone;
import com.sns.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class Hibernate1 {
    public static final Log log = LogFactory.getLog(Hibernate1.class);

    public static void main(String[] args) {
//        updateUsers("blabla:");
//        printUsers("bla: ");
//        testBla();
        fillCat();
    }

    public static void fillCat() {
        
        Session s = HU.getSession();
        Transaction tx = HU.getTransaction();
        Query q = s.createQuery("from Phone p where p.id = 2 ");
        List res = q.list();
        Phone p = (Phone) res.get(0);
        p.setNumber("anothernumber");
        s.flush();
        tx.commit();
    }

    public static void testBla() {
        Session s = HU.getSession();
        Transaction tx = HU.getTransaction();
        Query q = s.createQuery("from Phone p where p.id = 1 ");
        List res = q.list();
        Phone p = (Phone) res.get(0);
        System.out.println(p);
        p.setNumber("xaxaxaxa");

        
        q = s.createQuery("from User u where u.id = 1 ");
        res = q.list();
        User user = (User) res.get(0);
        for (Iterator it = user.getPhoneList().iterator(); it.hasNext();) {
            Phone phone = (Phone) it.next();
            System.out.println(phone + " " + (phone == p));
        }
        Phone p2 = new Phone("blaxa33bla");
        p2.setId(new Integer(2));
        p2.setUser(user);
        List phones = user.getPhoneList();
        Phone oldPhone = null;
        for(int i =0; i < phones.size(); i++){
            oldPhone = (Phone) phones.get(i);
            if(oldPhone.getId().equals(new Integer(2))){
                phones.remove(i);
                oldPhone.setUser(null);
                phones.add(i,p2);
                break;
            }
        }
//        s.evict(oldPhone);
        s.update(user);
        tx.commit();

    }

    public static void printUsers(String prefix) {
        Session s = HU.getSession();
        ///
        Query q = s.createQuery("from User");
        List res = q.list();
        for (Iterator iterator = res.iterator(); iterator.hasNext();) {
            Object o =  iterator.next();
            System.out.println(prefix+" : "+o);
        }
        ///
    }

    public static void updateUsers(String prefix) {
        Session s = HU.getSession();
        ///
        Transaction tx = HU.getTransaction();
        Query q = s.createQuery("from User");
        List res = q.list();
        int i=0;
        for (Iterator iterator = res.iterator(); iterator.hasNext();) {
            User user = (User) iterator.next();
            Phone o = new Phone("335x44x" + i);
            o.setUser(user);
            user.getPhoneList().add(o);
            Phone phone = new Phone("3x5x2323" + i);
            phone.setUser(user);
            user.getPhoneList().add(phone);
            Phone o1 = new Phone("33545xx45" + i);
            o1.setUser(user);
            user.getPhoneList().add(o1);
            System.out.println(prefix+" : "+user);
            i++;
            HU.getSession().update(user);
        }
        ///
        tx.commit();
    }
}
