package uz.sportloto.paynet.dao.hibernate;

import net.sf.hibernate.mapping.Column;
import net.sf.hibernate.mapping.PersistentClass;
import net.sf.hibernate.mapping.PrimaryKey;
import uz.sportloto.paynet.model.response.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

class ExistsQueryBuilder extends QueryBuilder {

    public ExistsQueryBuilder(Class aClass) {
        final PersistentClass classMapping = BaseHibernateDAO.configuration.getClassMapping(aClass);
        StringBuffer sb = new StringBuffer("select count(1) from ").append(classMapping.getTable().getSchema()).
                append(".").append(classMapping.getTable().getName()).append(" WHERE 1=1 ");
        // adding PK condition
        final PrimaryKey key = classMapping.getTable().getPrimaryKey();
        final Iterator pkIterator = key.getColumnIterator();
        while (pkIterator.hasNext()) {
            Column column = (Column) pkIterator.next();
            sb.append(" AND ").append(column.getName()).append(" = ? ");
        }
        sql = sb.toString();
        System.out.println(sql);
    }

    protected void map0(PreparedStatement ps, Category entity) throws SQLException {
        ps.setInt(1, entity.getCategoryId());
    }

    protected void map0(PreparedStatement ps, ServiceType entity) throws SQLException {
        ps.setInt(1, entity.getServiceTypeId());
    }

    protected void map0(PreparedStatement ps, Service entity) throws SQLException {
        ps.setInt(1, entity.getServiceId());
    }

    protected void map0(PreparedStatement ps, Field entity) throws SQLException {
        ps.setInt(1, entity.getServiceType().getServiceTypeId());
        ps.setString(2, entity.getName());
    }

    protected void map0(PreparedStatement ps, Provider entity) throws SQLException {
        ps.setInt(1, entity.getProviderId());
    }


}
