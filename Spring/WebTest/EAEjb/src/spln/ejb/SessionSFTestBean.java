package spln.ejb;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.SessionBean;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.EJBException;

/** @noinspection ALL*/
public class SessionSFTestBean implements SessionBean {

    private static final Log log = LogFactory.getLog(SessionSFTestBean.class);
    private static int COUNTER = 0;
    private int id = -1;

    public SessionSFTestBean() {
        id = COUNTER++;
        log.info("Stateless bean constructed. id = "+ id);
    }

    public void ejbCreate() throws CreateException {
        log.info("ejbCreate id="+id);
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {
        log.info("setSessionContext id="+id);
    }

    public void ejbRemove() throws EJBException {
        log.info("ejbRemove id="+id);
    }

    public void ejbActivate() throws EJBException {
        log.info("ejbActivate id="+id);
    }

    public void ejbPassivate() throws EJBException {
        log.info("ejbPassivate id="+id);
    }

    public Long getBeanInstanceId() throws EJBException {
        return new Long(id);
    }
    public String executeQuickMethod() throws EJBException{
        log.info("executing quick method in statefull bean");
        return "quick method executed in statefull bean";
    }

    public String executeLongMethod(Long time) throws EJBException{
        log.info("Executing long method...");
        try {
            log.info("Waiting for "+time+"milliseconds");
            Thread.sleep(time.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("long method call finished");
        return "long method executed in statefull bean";
    }
}
