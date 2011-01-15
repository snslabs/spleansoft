package spln.ejb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.SessionBean;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

/** @noinspection ALL*/
public class SessionSLTestBean implements SessionBean {
    private static final Log log = LogFactory.getLog(SessionSLTestBean.class);
    private static int COUNTER = 0;
    private int id = -1;
    public SessionSLTestBean() {
        id = COUNTER++;
        log.info("Stateless bean constructed. COUNTER = "+ id);
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

    public Long getBeanInstanceId() {
        return new Long(id);
    }
    public String executeQuickMethod() throws EJBException{
        log.info("executing quick method in stateless bean id="+id);
        return "quick method executed in stateless bean id="+id;
    }

    public String executeLongMethod(Long time) throws EJBException{
        log.info("Executing long method...");
        try {
            log.info("Waiting for "+time+"milliseconds id="+id);
            Thread.sleep(time.longValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("long method call finished id="+id);
        return "long method executed in stateless bean id="+id;
    }

    public String externalTransactionalMethod(Boolean toFail, Boolean toFailBefore,
                                              Boolean callInnerLocally,
                                              Boolean failInner) throws EJBException{
        if(toFail.booleanValue() && toFailBefore.booleanValue()){
            throw new EJBException("Method failed before inner method executed!");
        }
        if(callInnerLocally.booleanValue()){
            transactionalMethod(failInner);
        }
        else{
            InitialContext ic = null;
            try {
                ic = new InitialContext();
                SessionSLTest sessionSLTest = ((SessionSLTestHome) (PortableRemoteObject.narrow(ic.lookup("ejb/SessionSLTestEJB"), SessionSLTestHome.class))).create();
                sessionSLTest.transactionalMethod(failInner);
            }
            catch (Exception e) {
                throw new EJBException("Cannot execute inner method remotely :-(",e);
            }
        }
        if(toFail.booleanValue() && !toFailBefore.booleanValue()){
            throw new EJBException("Method failed after inner method executed!");
        }
        return "OK";
    }

    public String transactionalMethod(Boolean toFail) throws EJBException{
        if(toFail.booleanValue()){
            throw new EJBException("Method call failed by user request ;-)");
        }
        return "OK";
    }

}
