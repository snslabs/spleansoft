package com.splean.web.file;

import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FilesFacadeFactory {

    private static FilesFacadeFactory instance;

    private  Map<String, FilesFacadeBackend> IMPLEMENTATIONS = new HashMap<String, FilesFacadeBackend>();

    private FilesFacadeFactory() {

        IMPLEMENTATIONS.put("ntfs", new FilesFacadeFileSystemImpl());
        IMPLEMENTATIONS.put("default", new FilesFacadeFileSystemImpl());
        InvocationHandler ih = new InvocationHandler() {
            private FilesFacadeFtpImpl impl = new FilesFacadeFtpImpl();
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Method m = FilesFacadeFtpImpl.class.getMethod(method.getName(), method.getParameterTypes());
                try{
                    if(!m.getName().equals("init")){
                        impl.initFtpClient();
                    }
                    return m.invoke(impl,args);
                }
                finally{
                    if(!m.getName().equals("init")){
                        impl.tearDownFtpClient();
                    }
                }
            }
        };
        Object ftpBackend = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{FilesFacadeBackend.class}, ih);
        IMPLEMENTATIONS.put("ftp", (FilesFacadeBackend) ftpBackend);

    }

    public static FilesFacadeFactory getInstance(){
        if(instance == null){
            instance = new FilesFacadeFactory();
        }
        return instance;
    }

    public FilesFacadeBackend getImplementation(String path) {
        
        try{
            String key;

            if(path.toLowerCase().startsWith("ftp://")){
                key = "ftp";
            }
            else{
                key = "ntfs";
            }

            final FilesFacadeBackend backend = IMPLEMENTATIONS.get(key);
            backend.init(path);
            return backend;

        }
        catch(Exception e){
            throw new RuntimeException("No implementation found",e);
        }

    }
}
