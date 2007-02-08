package com.sns;

import org.hibernate.cfg.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Hibernate1 {
    public static final Log log = LogFactory.getLog(Hibernate1.class);

    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        log.debug("Hello!");
    }
}
