package com.sns;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

public class Hibernate1 {
    public static final Log log = LogFactory.getLog(Hibernate1.class);

    public static void main(String[] args) {
        new Hibernate1().main();
    }

    private void main() {
        log.debug("Hello!");
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
}
