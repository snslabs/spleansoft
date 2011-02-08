package uz.sportloto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DataSourceProvider {
    private static final Log log = LogFactory.getLog(DataSourceProvider.class);
    private static final String DATASOURCE_JNDI = "java:/comp/env/jdbc/sportlotto";
    private DataSource dataSource;
    public DataSource getDataSource() throws Exception {
        if(dataSource == null){
            Context ctx;
            try {
                ctx = new InitialContext();
            }
            catch (NamingException e) {
                log.fatal("Cannot get DataSource",e);
                return null;
            }

            try {
                Object value = ctx.lookup(DATASOURCE_JNDI);
                log.debug("DataSource lookup");
                log.debug("jdbc value : " + value);
                log.debug("jdbc class : " + value.getClass().getName());

                if (value instanceof DataSource) {
                    dataSource = (DataSource) value;
                }
                else {
                    log.fatal("Cannot get DataSource. The instance at " + DATASOURCE_JNDI + " is "+
                            value==null?"null":value.getClass());
                }

            } catch (NamingException e) {
                log.fatal("JNDI lookup failed!",e);
            }
        }
        return dataSource;
    }
}
