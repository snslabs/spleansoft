package spln.www;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

public class SpleanSessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("session created: "+httpSessionEvent.getSession().getId());

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("session destroyed: "+httpSessionEvent.getSession().getId());
    }
}
