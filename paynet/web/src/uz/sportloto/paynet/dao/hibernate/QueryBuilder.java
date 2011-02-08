package uz.sportloto.paynet.dao.hibernate;

import uz.sportloto.paynet.model.response.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
* User: CLUSERG
* Date: 11.03.2008
* Time: 20:37:40
* To change this template use File | Settings | File Templates.
*/
public abstract class QueryBuilder {
    protected String sql;

    public String getSql() {
        return this.sql;
    }
    
    public void map(PreparedStatement ps, Object entity) throws SQLException {
        if (entity instanceof Field) {
            map0(ps, (Field) entity);
        } else if (entity instanceof Provider) {
            map0(ps, (Provider) entity);
        } else if (entity instanceof Service) {
            map0(ps, (Service) entity);
        } else if (entity instanceof ServiceType) {
            map0(ps, (ServiceType) entity);
        } else if (entity instanceof Category) {
            map0(ps, (Category) entity);
        } else {
            throw new IllegalArgumentException("Cannot map " + (entity != null ? entity.getClass() : "null"));
        }
    }

    protected abstract void map0(PreparedStatement ps, Category entity) throws SQLException;

    protected abstract void map0(PreparedStatement ps, ServiceType entity) throws SQLException;

    protected abstract void map0(PreparedStatement ps, Service entity) throws SQLException;

    protected abstract void map0(PreparedStatement ps, Field entity) throws SQLException;

    protected abstract void map0(PreparedStatement ps, Provider entity) throws SQLException;
}
