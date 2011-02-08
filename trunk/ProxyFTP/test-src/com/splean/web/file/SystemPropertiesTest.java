package com.splean.web.file;

import junit.framework.TestCase;

import java.util.Properties;
import java.util.Map;

public class SystemPropertiesTest extends TestCase {
    public void testSystemProperties(){
        final Properties properties = System.getProperties();
        for (Map.Entry<Object, Object> e : properties.entrySet()) {
            if("ru".equalsIgnoreCase(e.getValue().toString())){
                System.out.println(e.getKey() + " : "+ e.getValue());
            }
        }
    }
}
