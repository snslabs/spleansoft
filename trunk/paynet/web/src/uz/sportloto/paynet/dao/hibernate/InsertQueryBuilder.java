package uz.sportloto.paynet.dao.hibernate;

import uz.sportloto.paynet.model.response.*;
import net.sf.hibernate.mapping.PersistentClass;
import net.sf.hibernate.mapping.Column;

import java.util.Iterator;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

class InsertQueryBuilder extends QueryBuilder {
    public InsertQueryBuilder(Class<? extends AbstractModel> aClass) {
        final PersistentClass classMapping = BaseHibernateDAO.configuration.getClassMapping(aClass);
        StringBuffer sb = new StringBuffer("insert into ").append(classMapping.getTable().getSchema()).
                append(".").append(classMapping.getTable().getName()).append(" ( ");
        StringBuffer valuesClause = new StringBuffer();
        // column names
        final Iterator columnIterator = classMapping.getTable().getColumnIterator();
        while (columnIterator.hasNext()) {
            Column column = (Column) columnIterator.next();
            sb.append(column.getName());
            valuesClause.append("?");
            if (columnIterator.hasNext()) {
                sb.append(", ");
                valuesClause.append(", ");
            }
        }
        //~
        sb.append(") values ( ").append(valuesClause).append(" ) ");
        sql = sb.toString();
        System.out.println(sql);
    }

    protected void map0(PreparedStatement ps, Category entity) throws SQLException {
        ps.setInt(1, entity.getCategoryId());
        ps.setString(2, entity.getName());
        ps.setString(3, String.valueOf(entity.isActive()));
        ps.setTimestamp(4, new Timestamp(entity.getCreatedOn().getTime()));
    }

    protected void map0(PreparedStatement ps, ServiceType entity) throws SQLException {
        // insert into LOTTO.TS_SERVICE_TYPE ( SERVICE_TYPE_ID, ACTIVE, CREATED_ON) values ( ?, ?, ? )
        ps.setInt(1, entity.getServiceTypeId());
        ps.setString(2, String.valueOf(entity.isActive()));
        ps.setTimestamp(3, new Timestamp(entity.getCreatedOn().getTime()));
    }

    protected void map0(PreparedStatement ps, Service entity) throws SQLException {
        // insert into LOTTO.TS_SERVICE ( SERVICE_ID, NAME, PROVIDER_ID, CATEGORY_ID, SERVICE_TYPE_ID, ACTIVE, CREATED_ON, CHEQUE_INFO) values ( ?, ?, ?, ?, ?, ?, ?, ? )
        ps.setInt(1, entity.getServiceId());
        ps.setString(2, entity.getName());
        ps.setInt(3, entity.getProviderId());
        ps.setInt(4, entity.getCategoryId());
        ps.setInt(5, entity.getServiceTypeId());
        ps.setString(6, String.valueOf(entity.isActive()));
        ps.setTimestamp(7, new Timestamp(entity.getCreatedOn().getTime()));
        ps.setString(8, entity.getChequeInfo());
        ps.setBigDecimal(9, entity.getServicePrice());
    }

    protected void map0(PreparedStatement ps, Field entity) throws SQLException {
        ps.setInt(1, entity.getServiceType().getServiceTypeId());
        ps.setString(2, entity.getName());
        ps.setString(3, entity.getLabel());
        ps.setString(4, entity.getType());
        ps.setInt(5, entity.getSize());
        ps.setString(6, entity.getRequired());
        ps.setString(7, String.valueOf(entity.isActive()));
        ps.setTimestamp(8, new Timestamp(entity.getCreatedOn().getTime()));
    }

    protected void map0(PreparedStatement ps, Provider entity) throws SQLException {
        // insert into LOTTO.TS_PROVIDER ( PROVIDER_ID, CATEGORY_ID, NAME, ACTIVE, CREATED_ON) values ( ?, ?, ?, ?, ? )
        ps.setInt(1, entity.getProviderId());
        ps.setInt(2, entity.getCategoryId());
        ps.setString(3, entity.getName());
        ps.setString(4, String.valueOf(entity.isActive()));
        ps.setTimestamp(5, new Timestamp(entity.getCreatedOn().getTime()));


    }
}
