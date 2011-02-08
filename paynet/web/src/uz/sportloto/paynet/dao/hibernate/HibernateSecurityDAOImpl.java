package uz.sportloto.paynet.dao.hibernate;

import uz.sportloto.paynet.dao.SecurityDAO;
import uz.sportloto.paynet.model.PaynetCredentials;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

public class HibernateSecurityDAOImpl extends BaseHibernateDAO implements SecurityDAO {
    public PaynetCredentials getPaynetCredentialsByTerminalId(int terminalId) throws HibernateException {
        final Session session = this.openSession();
        try {
            return (PaynetCredentials) session.get(PaynetCredentials.class, terminalId);
        }
        finally {
            session.close();
        }
    }
}
