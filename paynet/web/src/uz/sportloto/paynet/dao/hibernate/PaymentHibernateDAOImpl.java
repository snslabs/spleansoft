package uz.sportloto.paynet.dao.hibernate;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.lob.ClobImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.dbcp.DelegatingConnection;
import uz.sportloto.paynet.dao.PaymentDAO;
import uz.sportloto.paynet.model.PaynetRequest;
import uz.sportloto.Configuration;

import java.util.List;
import java.sql.*;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.OutputStream;

import oracle.sql.CLOB;
import oracle.sql.BLOB;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;

public class PaymentHibernateDAOImpl extends BaseHibernateDAO implements PaymentDAO {

    private static final Log log = LogFactory.getLog(PaymentHibernateDAOImpl.class);

    public void savePayment(PaynetRequest paynetRequest) throws HibernateException {
        Session session = openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(paynetRequest);
            tx.commit();
            Connection conn = session.connection();
            if (conn instanceof DelegatingConnection) {
                conn = ((DelegatingConnection) conn).getInnermostDelegate();
            }
            if (paynetRequest.getResponse() != null) {
                try {
                    PreparedStatement ps = conn.prepareStatement("update LOTTO.T_PAYMENT set XML_RESPONSE = empty_blob() where PAYMENT_ID = ? ");
                    ps.setLong(1, paynetRequest.getId());
                    ps.executeUpdate();
                    ps.close();

                    ps = conn.prepareStatement("select XML_RESPONSE from LOTTO.T_PAYMENT where PAYMENT_ID = ? FOR UPDATE");
                    ps.setLong(1, paynetRequest.getId());
                    final ResultSet resultSet = ps.executeQuery();
                    if (resultSet.next()) {
                        BLOB blob = ((OracleResultSet) resultSet).getBLOB(1);
                        final OutputStream os = blob.getBinaryOutputStream();
                        os.write(paynetRequest.getResponse().getBytes(Configuration.getBlobEncoding()));
                        os.close();
                    }
                    resultSet.close();
                    ps.close();
                    conn.commit();
                }
                catch (SQLException e) {
                    conn.rollback();
                    log.error(e);
                } catch (IOException e) {
                    conn.rollback();
                    log.error(e);
                }
            }
            session.close();
        }
        catch (SQLException e) {
            tx.rollback();
            session.close();
            log.error(e);
            throw new HibernateException(e);
        }
        catch (HibernateException e) {
            tx.rollback();
            session.close();
            log.error(e);
            throw e;
        }
    }

    public PaynetRequest findPaynetRequestByChequeId(String internalChequeId, String paynetChequeId) throws HibernateException {
        Session session = openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query findQ = session.getNamedQuery("findPaynetRequestByBothChequeIds");
            findQ.setParameter("internalChequeId", internalChequeId);
            findQ.setParameter("paynetChequeId", paynetChequeId);
            List list = findQ.list();
            return list.size() > 0 ? (PaynetRequest) list.get(0) : null;
        }
        catch (HibernateException e) {
            tx.rollback();
            session.close();
            log.error(e);
            throw e;
        }
        finally {
            tx.commit();
            session.close();
        }
    }

    public void clearAllpaynetRequests() throws HibernateException {
        Session session = openSession();
        Transaction tx = session.beginTransaction();
        try {

            List list = session.createQuery("from PaynetRequest").list();
            for (Object aList : list) {
                session.delete(aList);
            }
        }
        catch (HibernateException e) {
            tx.rollback();
            log.error(e);
            throw e;
        }
        finally {
            tx.commit();
            session.close();
        }
    }

    public boolean isDuplicate(PaynetRequest paynetRequest) throws HibernateException {
        return findPaynetRequestByChequeId(paynetRequest.getInternalChequeId(), null) != null;
    }
}
