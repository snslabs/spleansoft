package uz.sportloto.paynet.dao;

import uz.sportloto.paynet.model.PaynetCredentials;
import net.sf.hibernate.HibernateException;

/**
 * This dao provides methods to work with security stored in database
 */
public interface SecurityDAO {

    PaynetCredentials getPaynetCredentialsByTerminalId(int terminalId) throws HibernateException;

}
