package com.sns.proxy;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UniversalProxy implements IF {
    private IF pr;

    public static void main(String[] args) throws Exception {
        UniversalProxy proxy = new UniversalProxy();
//        proxy.call();
        Method m = IF.class.getMethod("call", null);
        m.invoke(proxy.pr, null);
//        proxy.pr.call();
    }

    public UniversalProxy() throws Exception {
        InvocationHandler ih = new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Method " + method.getName() + " was called via proxy! {" + args + "}");
                return null;
            }
        };
        MyClassLoader cl = new MyClassLoader(IF.class.getClassLoader());
        pr = (IF) Proxy.newProxyInstance(cl, new Class[]{IF.class}, ih);
    }

    public void call() {
        System.out.println("Call method executed");
    }

    public Class extractInterface(Class clazz) {
        StringBuffer sb = new StringBuffer();
        Method[] methods = clazz.getMethods();
        String pckgName = clazz.getPackage().toString();
        sb.append("package ").append(pckgName).append(";\n");
        sb.append("public interface ").append("Interface$Name").append(" {");
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            sb.append("\t").append(method.getReturnType().toString()).append(" ")
                    .append(method.getName()).append(" (");
            Class[] parameters = method.getParameterTypes();
            for (int j = 0; j < parameters.length; j++) {
                Class parameter = parameters[j];
                sb.append(parameter.toString()).append(" arg").append(j).append("");
                if (j != (parameters.length - 1)) {
                    sb.append(", ");
                }
            }

            sb.append(") throws Exception;");
        }
        sb.append("}");

        clazz.getClassLoader();

        return clazz;
    }
}

class MyClassLoader extends ClassLoader {

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class loadClass(String name) throws ClassNotFoundException {
        System.out.println("loading class using My classloader");
        byte[] bytecode = new byte[4096];
        Class c = super.loadClass(name);
        if (c == null) {
//            c = new Class();
            c = defineClass(name, bytecode, 0, bytecode.length);
        }
        return c;
    }
}

interface IF {
    void call();
}
