package uz.sportloto.paynet.dao.hibernate;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uz.sportloto.paynet.dao.ProvidersDAO;
import uz.sportloto.paynet.model.response.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.*;

public class ProvidersHibernateDAOImpl extends BaseHibernateDAO implements ProvidersDAO {

    private static Log log = LogFactory.getLog(ProvidersHibernateDAOImpl.class);
    public void saveEntireProvidersList(Collection<Provider> allProviders) throws HibernateException {
//        final Session baseSession = openSession();
//        final Session interceptorSession = openSession(baseSession.connection());
//        final Session session = openSession(baseSession.connection(), new CheckExistenceInterceptor(interceptorSession));
        final Session session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            try {
                deactivateAll(session);
            } catch (SQLException e) {
                throw new HibernateException(e);
            }
            Set<Field> fields = new HashSet<Field>();
            Set<Category> categories = new HashSet<Category>();
            Set<ServiceType> serviceTypes = new HashSet<ServiceType>();
            Set<Service> services = new HashSet<Service>();

            for (Provider provider : allProviders) {
                services.addAll(provider.getServices());
                categories.add(provider.getCategory());
            }
            for (Service service : services) {
                serviceTypes.add(service.getServiceType());
            }
            for(ServiceType serviceType : serviceTypes){
                fields.addAll(serviceType.getFields());
            }

            log.info(MessageFormat.format("Going to save:\nCategories: {4}\nProviders: {0}\nServices: {1}\nService Types: {2}\nFields: {3}",
                    allProviders.size(), services.size(), serviceTypes.size(), fields.size(), categories.size()));
            for (Category category : categories) {
                persist(category, session);
            }
            for (ServiceType serviceType : serviceTypes) {
                persist(serviceType, session);
            }
            for (Provider provider : allProviders) {
                persist(provider, session);
            }
            for (Field field : fields) {
                persist(field, session);
            }
            for (Service service : services) {
                persist(service, session);
            }
            tx.commit();
        }
        catch (HibernateException e) {
            if(tx != null){
                tx.rollback();
            }
            log.error(e);
            throw e;
        }
        finally {
            session.close();
//            interceptorSession.close();
//            baseSession.close();
        }


    }

    private void deactivateAll(Session session) throws HibernateException, SQLException {
        session.connection().createStatement().executeUpdate("update lotto.TS_field set active='N' ");
        session.connection().createStatement().executeUpdate("update lotto.TS_service set active='N' ");
        session.connection().createStatement().executeUpdate("update lotto.TS_service_type set active='N' ");
        session.connection().createStatement().executeUpdate("update lotto.TS_provider set active='N' ");
        session.connection().createStatement().executeUpdate("update lotto.TS_category set active='N' ");
    }

    public List<Provider> loadProvidersList() throws HibernateException {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        try {
            List list = session.createQuery("from Provider where active='Y' order by category_id").list();
            transaction.commit();
            //noinspection unchecked
            return Collections.unmodifiableList(list);
        }
        catch (HibernateException e) {
            transaction.rollback();
            log.error(e);
            throw e;
        }
        finally {
            session.close();
        }
    }

    public Map<Integer, Service> loadServicesMap() throws HibernateException {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        try {
            List list = session.createQuery("from Service order by name").list();
            transaction.commit();
            Map<Integer, Service> map = new HashMap<Integer, Service>();
            for (Object o : list) {
                final Service service = (Service) o;
                map.put(service.getServiceId(), service);
            }
            return Collections.unmodifiableMap(map);
        }
        catch (HibernateException e) {
            transaction.rollback();
            log.error(e);
            throw e;
        }
        finally {
            session.close();
        }
    }

    /**
     * Simply deletes all info from tables related to providers. Use with caution!
     * @throws HibernateException if something wrong with hibernate configuration
     * @throws SQLException is something wrong with SQL
     */
    public void deleteAllProviders() throws HibernateException {
        Session session = openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.connection().createStatement().executeUpdate("delete from lotto.TS_cheque_info");
            session.connection().createStatement().executeUpdate("delete from lotto.TS_field");
            session.connection().createStatement().executeUpdate("delete from lotto.TS_service");
            session.connection().createStatement().executeUpdate("delete from lotto.TS_service_type");
            session.connection().createStatement().executeUpdate("delete from lotto.TS_provider");
            session.connection().createStatement().executeUpdate("delete from lotto.TS_category");
            tx.commit();
        }
        catch (HibernateException e) {
            tx.rollback();
            log.error(e);
            throw e;
        }
        catch (SQLException e) {
            tx.rollback();
            log.error(e);
            throw new HibernateException(e);
        } finally {
            session.close();
        }

    }

    public Date getProvidersUpdatedOn() throws HibernateException {
        Session session = openSession();
        Transaction tx = session.beginTransaction();
        Date result = null;
        try {
            final Statement st = session.connection().createStatement();
            ResultSet rs = st.executeQuery("select max(CREATED_ON) from LOTTO.TS_PROVIDER");
            if(rs.next()){
                result =  rs.getTimestamp(1);
            }
            st.close();
            tx.commit();
        }
        catch (HibernateException e) {
            tx.rollback();
            log.error(e);
            throw e;
        }
        catch (SQLException e) {
            tx.rollback();
            log.error(e);
            throw new HibernateException(e);
        } finally {
            session.close();
        }
        return result!=null?result:new Date(0);
    }
}
