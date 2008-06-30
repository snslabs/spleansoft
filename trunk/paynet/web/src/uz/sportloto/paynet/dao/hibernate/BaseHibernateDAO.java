package uz.sportloto.paynet.dao.hibernate;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;
import net.sf.hibernate.mapping.PersistentClass;
import uz.sportloto.paynet.model.response.AbstractModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

abstract public class BaseHibernateDAO {
    private static SessionFactory sessionFactory;
    protected static Configuration configuration;
    private static Map<String, QueryBuilder> queryCache;

    static {
        try {
            BaseHibernateDAO.configuration = new Configuration().configure("/uz/sportloto/paynet/dao/hibernate/resources/hibernate.cfg.xml");
            sessionFactory =
                    BaseHibernateDAO.configuration.
                            buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        queryCache = new HashMap<String, QueryBuilder>();
    }

    protected Session openSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    protected Session openSession(Connection connection) throws HibernateException {
        return sessionFactory.openSession(connection);
    }

    protected Session openSession(Connection connection, Interceptor interceptor) {
        return sessionFactory.openSession(connection, interceptor);
    }


    protected Session openSession(Interceptor interceptor) throws HibernateException {
        return sessionFactory.openSession(interceptor);
    }

    protected PersistentClass getClassMapping(Class clazz) {
        return configuration.getClassMapping(clazz);
    }

    protected void persist(AbstractModel model, Session session) throws HibernateException {
        try {
            if (!exists(model, session)) {
                insert(model, session);
            } else {
                update(model, session);
            }
        }
        catch (Exception e) {
            throw new HibernateException(e);
        }
    }

    private void update(AbstractModel model, Session session) throws HibernateException, SQLException {
        final QueryBuilder qb = getUpdateQueryBuilder(model.getClass());
        final PreparedStatement ps = session.connection().prepareStatement(qb.getSql());
        try {
            qb.map(ps, model);
            ps.executeUpdate();
        }
        finally {
            ps.close();
        }
    }

    private void insert(AbstractModel model, Session session) throws HibernateException, SQLException {
        final QueryBuilder qb = getInsertQueryBuilder(model.getClass());
        final PreparedStatement ps = session.connection().prepareStatement(qb.getSql());
        try {
            qb.map(ps, model);
            ps.executeUpdate();
        }
        finally {
            ps.close();
        }
    }

    private boolean exists(AbstractModel model, Session session) throws HibernateException, SQLException {
        final QueryBuilder qb = getExistsQueryBuilder(model.getClass());
        final PreparedStatement ps = session.connection().prepareStatement(qb.getSql());
        ResultSet rs = null;
        try {
            qb.map(ps, model);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            ps.close();
        }
        return false;
    }

    private QueryBuilder getInsertQueryBuilder(Class<? extends AbstractModel> aClass) {
        QueryBuilder qb = queryCache.get("insert." + aClass.getName());
        if (qb == null) {
            qb = new InsertQueryBuilder(aClass);
        }
        return qb;
    }
    private QueryBuilder getUpdateQueryBuilder(Class<? extends AbstractModel> aClass) {
        QueryBuilder qb = queryCache.get("update." + aClass.getName());
        if (qb == null) {
            qb = new UpdateQueryBuilder(aClass);
        }
        return qb;
    }
    private QueryBuilder getExistsQueryBuilder(Class<? extends AbstractModel> aClass) {
        QueryBuilder qb = queryCache.get("exists." + aClass.getName());
        if (qb == null) {
            qb = new ExistsQueryBuilder(aClass);
        }
        return qb;
    }


}

