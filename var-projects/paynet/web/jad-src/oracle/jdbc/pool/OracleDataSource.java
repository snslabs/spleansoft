/* Decompiled through IntelliJad */
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packfields(3) packimports(3) splitstr(64) radix(10) lradix(10) 
// Source File Name:   OracleDataSource.java

package oracle.jdbc.pool;

import java.io.*;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.*;
import javax.sql.DataSource;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.driver.*;

// Referenced classes of package oracle.jdbc.pool:
//            OracleConnectionCacheManager, OracleImplicitConnectionCache

public class OracleDataSource
    implements DataSource, Serializable, Referenceable
{

    public OracleDataSource()
        throws SQLException
    {
        logWriter = null;
        loginTimeout = 0;
        databaseName = null;
        serviceName = null;
        dataSourceName = "OracleDataSource";
        description = null;
        networkProtocol = "tcp";
        portNumber = 0;
        user = null;
        password = null;
        serverName = null;
        url = null;
        driverType = null;
        tnsEntry = null;
        maxStatements = 0;
        implicitCachingEnabled = false;
        explicitCachingEnabled = false;
        odsCache = null;
        cacheManager = null;
        connCacheName = null;
        connCacheProperties = null;
        connectionProperties = null;
        connCachingEnabled = false;
        fastConnFailover = false;
        onsConfigStr = null;
        isOracleDataSource = true;
        urlExplicit = false;
        useDefaultConnection = false;
        driver = new OracleDriver();
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.OracleDataSource()", this);
            OracleLog.recursiveTrace = false;
        }
        processFastConnectionFailoverSysProperty();
    }

    void processFastConnectionFailoverSysProperty()
    {
        if(isOracleDataSource && fastConnectionFailoverSysProperty)
        {
            connCachingEnabled = true;
            if(cacheManager == null)
                try
                {
                    cacheManager = OracleConnectionCacheManager.getConnectionCacheManagerInstance();
                }
                catch(SQLException e)
                {
                    if(TRACE && !OracleLog.recursiveTrace)
                    {
                        OracleLog.recursiveTrace = true;
                        OracleLog.poolLogger.log(Level.FINER, "OracleDataSource.processFastConnectionFailoverSysProperty()" + e, this);
                        OracleLog.recursiveTrace = false;
                    }
                }
            fastConnFailover = true;
            setSpawnNewThreadToCancel(true);
        }
    }

    public Connection getConnection()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getConnection()", this);
            OracleLog.recursiveTrace = false;
        }
        String localUser = null;
        String localPassword = null;
        synchronized(this)
        {
            localUser = user;
            localPassword = password;
        }
        return getConnection(localUser, localPassword);
    }

    public Connection getConnection(String _user, String _passwd)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getConnection(user=" + _user + ", passwd=" + _passwd + ")", this);
            OracleLog.recursiveTrace = false;
        }
        Connection conn = null;
        Properties prop = null;
        if(connCachingEnabled)
        {
            conn = getConnection(_user, _passwd, null);
        } else
        {
            synchronized(this)
            {
                makeURL();
                if(TRACE && !OracleLog.recursiveTrace)
                {
                    OracleLog.recursiveTrace = true;
                    OracleLog.poolLogger.log(Level.FINER, "OracleDataSource.getConnection(user, passwd): URL is" + url, this);
                    OracleLog.recursiveTrace = false;
                }
                prop = connectionProperties != null ? (Properties)connectionProperties.clone() : new Properties();
                if(url != null)
                    prop.setProperty("connection_url", url);
                if(_user != null)
                    prop.setProperty("user", _user);
                if(_passwd != null)
                    prop.setProperty("password", _passwd);
                if(loginTimeout != 0)
                    prop.setProperty("LoginTimeout", "" + loginTimeout);
                if(maxStatements != 0)
                    prop.setProperty("stmt_cache_size", "" + maxStatements);
            }
            conn = getPhysicalConnection(prop);
            if(conn == null)
                DatabaseError.throwSqlException(67);
        }
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getConnection(user, passwd): returned " + conn, this);
            OracleLog.recursiveTrace = false;
        }
        return conn;
    }

    protected Connection getPhysicalConnection(Properties prop)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.driverLogger.log(Level.FINE, "OracleDataSource.getPhysicalConnection(prop=" + prop + ")", this);
            OracleLog.recursiveTrace = false;
        }
        Connection conn = null;
        Properties localProps = prop;
        String localUrl = prop.getProperty("connection_url");
        String localUser = prop.getProperty("user");
        String localPassword = localProps.getProperty("password");
        String temp = null;
        boolean localUseDefaultConnection = false;
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.driverLogger.log(Level.FINER, "OracleDataSource.getPhysicalConnection(prop): URL is" + localUrl + ", user: " + localUser + ", password: " + localPassword, this);
            OracleLog.recursiveTrace = false;
        }
        synchronized(this)
        {
            if(connectionProperties != null)
            {
                localProps = (Properties)connectionProperties.clone();
                if(localUser != null)
                    localProps.put("user", localUser);
                if(localPassword != null)
                    localProps.put("password", localPassword);
            }
            if(localUser == null && user != null)
                localProps.put("user", user);
            if(localPassword == null && password != null)
                localProps.put("password", password);
            if(localUrl == null)
                localUrl = url;
            String localLoginTimeout = prop.getProperty("LoginTimeout");
            if(localLoginTimeout != null)
                localProps.put("oracle.net.CONNECT_TIMEOUT", "" + Integer.parseInt(localLoginTimeout) * 1000);
            localUseDefaultConnection = useDefaultConnection;
            if(driver == null)
                driver = new OracleDriver();
        }
        if(localUseDefaultConnection)
            conn = driver.defaultConnection();
        else
            conn = driver.connect(localUrl, localProps);
        if(conn == null)
            DatabaseError.throwSqlException(67);
        temp = prop.getProperty("stmt_cache_size");
        int statementCacheSize = 0;
        if(temp != null)
            ((OracleConnection)conn).setStatementCacheSize(statementCacheSize = Integer.parseInt(temp));
        boolean explicitStatementCachingEnabled = false;
        temp = prop.getProperty("ExplicitStatementCachingEnabled");
        if(temp != null)
            ((OracleConnection)conn).setExplicitCachingEnabled(explicitStatementCachingEnabled = temp.equals("true"));
        boolean implicitStatementCachingEnabled = false;
        temp = prop.getProperty("ImplicitStatementCachingEnabled");
        if(temp != null)
            ((OracleConnection)conn).setImplicitCachingEnabled(implicitStatementCachingEnabled = temp.equals("true"));
        if(statementCacheSize > 0 && !explicitStatementCachingEnabled && !implicitStatementCachingEnabled)
        {
            ((OracleConnection)conn).setImplicitCachingEnabled(true);
            ((OracleConnection)conn).setExplicitCachingEnabled(true);
        }
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.driverLogger.log(Level.FINE, "OracleDataSource.getPhysicalConnection(Properties): returned " + conn, this);
            OracleLog.recursiveTrace = false;
        }
        return conn;
    }

    public Connection getConnection(Properties cachedConnectionAttributes)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getConnection(cachedConnectionAttributes=" + cachedConnectionAttributes + ")", this);
            OracleLog.recursiveTrace = false;
        }
        String localUser = null;
        String localPassword = null;
        synchronized(this)
        {
            if(!connCachingEnabled)
                DatabaseError.throwSqlException(137);
            localUser = user;
            localPassword = password;
        }
        Connection conn = getConnection(localUser, localPassword, cachedConnectionAttributes);
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.driverLogger.log(Level.FINE, "OracleDataSource.getConnection(cachedConnectionAttributes) retur" +
"ned"
 + conn, this);
            OracleLog.recursiveTrace = false;
        }
        return conn;
    }

    public Connection getConnection(String _user, String _passwd, Properties cachedConnectionAttributes)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getConnection(user=" + _user + ", passwd=" + _passwd + ", cachedConnectionAttributes=" + cachedConnectionAttributes + ")", this);
            OracleLog.recursiveTrace = false;
        }
        if(!connCachingEnabled)
            DatabaseError.throwSqlException(137);
        if(odsCache == null)
            cacheInitialize();
        Connection conn = odsCache.getConnection(_user, _passwd, cachedConnectionAttributes);
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.driverLogger.log(Level.FINE, "OracleDataSource.getConnection(user, password, cachedConnectionA" +
"ttributes) returned"
 + conn, this);
            OracleLog.recursiveTrace = false;
        }
        return conn;
    }

    private synchronized void cacheInitialize()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.cacheInitialize()", this);
            OracleLog.recursiveTrace = false;
        }
        if(odsCache == null)
            if(connCacheName != null)
                cacheManager.createCache(connCacheName, this, connCacheProperties);
            else
                connCacheName = cacheManager.createCache(this, connCacheProperties);
    }

    public synchronized void close()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.close()", this);
            OracleLog.recursiveTrace = false;
        }
        if(connCachingEnabled && odsCache != null)
        {
            cacheManager.removeCache(odsCache.cacheName, 0L);
            odsCache = null;
        }
    }

    public synchronized void setConnectionCachingEnabled(boolean flag)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setConnectionCachingEnabled(" + flag + ")", this);
            OracleLog.recursiveTrace = false;
        }
        if(isOracleDataSource)
        {
            if(flag)
            {
                connCachingEnabled = true;
                if(cacheManager == null)
                    cacheManager = OracleConnectionCacheManager.getConnectionCacheManagerInstance();
            } else
            if(odsCache == null)
            {
                connCachingEnabled = false;
                fastConnFailover = false;
                setSpawnNewThreadToCancel(false);
                connCacheName = null;
                connCacheProperties = null;
            } else
            if(TRACE && !OracleLog.recursiveTrace)
            {
                OracleLog.recursiveTrace = true;
                OracleLog.poolLogger.log(Level.FINER, "OracleDataSource.setConnectionCachingEnabled()Can't disable conn" +
"ection caching without calling close()"
, this);
                OracleLog.recursiveTrace = false;
            }
        } else
        {
            DatabaseError.throwSqlException(137);
        }
    }

    public boolean getConnectionCachingEnabled()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getConnectionCachingEnabled()", this);
            OracleLog.recursiveTrace = false;
        }
        return connCachingEnabled;
    }

    public synchronized void setConnectionCacheName(String cacheName)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setCacheName(cacheName=" + cacheName + ")", this);
            OracleLog.recursiveTrace = false;
        }
        if(connCachingEnabled)
            if(cacheName == null)
                DatabaseError.throwSqlException(138);
            else
                connCacheName = cacheName;
    }

    public String getConnectionCacheName()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getCacheName()", this);
            OracleLog.recursiveTrace = false;
        }
        if(connCachingEnabled && odsCache != null)
            return odsCache.cacheName;
        else
            return connCacheName;
    }

    public synchronized void setConnectionCacheProperties(Properties cp)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setCacheProperties(cp=" + cp + ")", this);
            OracleLog.recursiveTrace = false;
        }
        if(connCachingEnabled)
            connCacheProperties = cp;
    }

    public Properties getConnectionCacheProperties()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getCacheProperties()", this);
            OracleLog.recursiveTrace = false;
        }
        if(connCachingEnabled && odsCache != null)
            return odsCache.getConnectionCacheProperties();
        else
            return connCacheProperties;
    }

    public synchronized void setFastConnectionFailoverEnabled(boolean flag)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setFastConnectionFailoverEnabled(" + flag + ")", this);
            OracleLog.recursiveTrace = false;
        }
        if(connCachingEnabled)
        {
            if(!fastConnFailover)
            {
                fastConnFailover = flag;
                setSpawnNewThreadToCancel(flag);
            } else
            if(!flag)
            {
                if(TRACE && !OracleLog.recursiveTrace)
                {
                    OracleLog.recursiveTrace = true;
                    OracleLog.poolLogger.log(Level.FINER, "OracleDataSource.setFastConnectionFailoverEnabled(): setting fla" +
"g to false is not allowed when the Fast Connection fail over is " +
"already set to true"
, this);
                    OracleLog.recursiveTrace = false;
                }
                DatabaseError.throwSqlException(255);
            }
        } else
        {
            DatabaseError.throwSqlException(137);
        }
    }

    public boolean getFastConnectionFailoverEnabled()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getFastConnectionFailoverEnabled()", this);
            OracleLog.recursiveTrace = false;
        }
        return fastConnFailover;
    }

    public String getONSConfiguration()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getONSConfiguration()", this);
            OracleLog.recursiveTrace = false;
        }
        return onsConfigStr;
    }

    public synchronized void setONSConfiguration(String onsConfigStr)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setONSConfiguration(" + onsConfigStr + ")", this);
            OracleLog.recursiveTrace = false;
        }
        this.onsConfigStr = onsConfigStr;
    }

    public synchronized int getLoginTimeout()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getLoginTimeout(): returned " + loginTimeout, this);
            OracleLog.recursiveTrace = false;
        }
        return loginTimeout;
    }

    public synchronized void setLoginTimeout(int timeout)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setLoginTimeout(" + timeout + ")", this);
            OracleLog.recursiveTrace = false;
        }
        loginTimeout = timeout;
    }

    public synchronized void setLogWriter(PrintWriter pw)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setLogWriter(" + pw + ")", this);
            OracleLog.recursiveTrace = false;
        }
        logWriter = pw;
        OracleLog.setLogWriter(pw);
    }

    public synchronized PrintWriter getLogWriter()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getLogWriter(): returned " + logWriter, this);
            OracleLog.recursiveTrace = false;
        }
        return logWriter;
    }

    public synchronized void setTNSEntryName(String dbname)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setTNSEntryName(" + dbname + ")", this);
            OracleLog.recursiveTrace = false;
        }
        tnsEntry = dbname;
    }

    public synchronized String getTNSEntryName()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getTNSEntryName(): returned " + tnsEntry, this);
            OracleLog.recursiveTrace = false;
        }
        return tnsEntry;
    }

    public synchronized void setDataSourceName(String dsname)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setDataSourceName(" + dsname + ")", this);
            OracleLog.recursiveTrace = false;
        }
        dataSourceName = dsname;
    }

    public synchronized String getDataSourceName()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getDataSourceName(): returned " + dataSourceName, this);
            OracleLog.recursiveTrace = false;
        }
        return dataSourceName;
    }

    public synchronized String getDatabaseName()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getDatabaseName(): returned " + databaseName, this);
            OracleLog.recursiveTrace = false;
        }
        return databaseName;
    }

    public synchronized void setDatabaseName(String dsname)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setDatabaseName(" + dsname + "): returned " + dsname, this);
            OracleLog.recursiveTrace = false;
        }
        databaseName = dsname;
    }

    public synchronized void setServiceName(String svcname)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setServiceName(" + svcname + ")", this);
            OracleLog.recursiveTrace = false;
        }
        serviceName = svcname;
    }

    public synchronized String getServiceName()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getServiceName: returned " + serviceName, this);
            OracleLog.recursiveTrace = false;
        }
        return serviceName;
    }

    public synchronized void setServerName(String sn)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setServerName(" + sn + "): returned " + sn, this);
            OracleLog.recursiveTrace = false;
        }
        serverName = sn;
    }

    public synchronized String getServerName()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getServerName(): returned " + serverName, this);
            OracleLog.recursiveTrace = false;
        }
        return serverName;
    }

    public synchronized void setURL(String url)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setURL(" + url + ")", this);
            OracleLog.recursiveTrace = false;
        }
        this.url = url;
        if(this.url != null)
            urlExplicit = true;
    }

    public synchronized String getURL()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getURL(): urlExplicit=" + urlExplicit, this);
            OracleLog.recursiveTrace = false;
        }
        if(!urlExplicit)
            makeURL();
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getURL(): returned " + url, this);
            OracleLog.recursiveTrace = false;
        }
        return url;
    }

    public synchronized void setUser(String userName)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setUser(" + user + "): returned " + user, this);
            OracleLog.recursiveTrace = false;
        }
        user = userName;
    }

    public String getUser()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getUser(): returned " + user, this);
            OracleLog.recursiveTrace = false;
        }
        return user;
    }

    public synchronized void setPassword(String pd)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setPassword(" + pd + "): returned " + pd, this);
            OracleLog.recursiveTrace = false;
        }
        password = pd;
    }

    protected String getPassword()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getPassword(): returned " + password, this);
            OracleLog.recursiveTrace = false;
        }
        return password;
    }

    public synchronized String getDescription()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getDescription(): returned " + description, this);
            OracleLog.recursiveTrace = false;
        }
        return description;
    }

    public synchronized void setDescription(String des)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setDescription(" + des + "): returned " + des, this);
            OracleLog.recursiveTrace = false;
        }
        description = des;
    }

    public synchronized String getDriverType()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getDriverType(): returned " + driverType, this);
            OracleLog.recursiveTrace = false;
        }
        return driverType;
    }

    public synchronized void setDriverType(String dt)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setDriverType(" + dt + ")", this);
            OracleLog.recursiveTrace = false;
        }
        driverType = dt;
    }

    public synchronized String getNetworkProtocol()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getNetworkProtocol(): returned " + networkProtocol, this);
            OracleLog.recursiveTrace = false;
        }
        return networkProtocol;
    }

    public synchronized void setNetworkProtocol(String np)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setNetworkProtocol(" + np + ")", this);
            OracleLog.recursiveTrace = false;
        }
        networkProtocol = np;
    }

    public synchronized void setPortNumber(int pn)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setPortNumber(" + pn + ")", this);
            OracleLog.recursiveTrace = false;
        }
        portNumber = pn;
    }

    public synchronized int getPortNumber()
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getPortNumber(): returned " + portNumber, this);
            OracleLog.recursiveTrace = false;
        }
        return portNumber;
    }

    public synchronized Reference getReference()
        throws NamingException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getReference()", this);
            OracleLog.recursiveTrace = false;
        }
        Reference ref = new Reference(getClass().getName(), "oracle.jdbc.pool.OracleDataSourceFactory", null);
        addRefProperties(ref);
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getReference(): returned " + ref, this);
            OracleLog.recursiveTrace = false;
        }
        return ref;
    }

    protected void addRefProperties(Reference ref)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.addRefProperties(" + ref + ")", this);
            OracleLog.recursiveTrace = false;
        }
        if(url != null)
            ref.add(new StringRefAddr("url", url));
        if(user != null)
            ref.add(new StringRefAddr("userName", user));
        if(password != null)
            ref.add(new StringRefAddr("passWord", password));
        if(description != null)
            ref.add(new StringRefAddr("description", description));
        if(driverType != null)
            ref.add(new StringRefAddr("driverType", driverType));
        if(serverName != null)
            ref.add(new StringRefAddr("serverName", serverName));
        if(databaseName != null)
            ref.add(new StringRefAddr("databaseName", databaseName));
        if(serviceName != null)
            ref.add(new StringRefAddr("serviceName", serviceName));
        if(networkProtocol != null)
            ref.add(new StringRefAddr("networkProtocol", networkProtocol));
        if(portNumber != 0)
            ref.add(new StringRefAddr("portNumber", Integer.toString(portNumber)));
        if(tnsEntry != null)
            ref.add(new StringRefAddr("tnsentryname", tnsEntry));
        if(maxStatements != 0)
            ref.add(new StringRefAddr("maxStatements", Integer.toString(maxStatements)));
        if(implicitCachingEnabled)
            ref.add(new StringRefAddr("implicitCachingEnabled", "true"));
        if(explicitCachingEnabled)
            ref.add(new StringRefAddr("explicitCachingEnabled", "true"));
        if(connCachingEnabled)
            ref.add(new StringRefAddr("connectionCachingEnabled", "true"));
        if(connCacheName != null)
            ref.add(new StringRefAddr("connectionCacheName", connCacheName));
        if(connCacheProperties != null)
            ref.add(new StringRefAddr("connectionCacheProperties", connCacheProperties.toString()));
        if(fastConnFailover)
            ref.add(new StringRefAddr("fastConnectionFailoverEnabled", "true"));
        if(onsConfigStr != null)
            ref.add(new StringRefAddr("onsConfigStr", onsConfigStr));
    }

    void makeURL()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.makeURL()", this);
            OracleLog.recursiveTrace = false;
        }
        if(urlExplicit)
            return;
        if(driverType == null || !driverType.equals("oci8") && !driverType.equals("oci") && !driverType.equals("thin") && !driverType.equals("kprb"))
            DatabaseError.throwSqlException(67, "OracleDataSource.makeURL");
        if(driverType.equals("kprb"))
        {
            useDefaultConnection = true;
            url = "jdbc:oracle:kprb:@";
            if(TRACE && !OracleLog.recursiveTrace)
            {
                OracleLog.recursiveTrace = true;
                OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.makeURL(): useDefaultConnection=" + useDefaultConnection + ", url=" + url, this);
                OracleLog.recursiveTrace = false;
            }
            return;
        }
        if((driverType.equals("oci8") || driverType.equals("oci")) && networkProtocol != null && networkProtocol.equals("ipc"))
        {
            url = "jdbc:oracle:oci:@";
            if(TRACE && !OracleLog.recursiveTrace)
            {
                OracleLog.recursiveTrace = true;
                OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.makeURL(): url=" + url, this);
                OracleLog.recursiveTrace = false;
            }
            return;
        }
        if(tnsEntry != null)
        {
            url = "jdbc:oracle:" + driverType + ":@" + tnsEntry;
            if(TRACE && !OracleLog.recursiveTrace)
            {
                OracleLog.recursiveTrace = true;
                OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.makeURL(): url=" + url, this);
                OracleLog.recursiveTrace = false;
            }
            return;
        }
        if(serviceName != null)
        {
            url = "jdbc:oracle:" + driverType + ":@(DESCRIPTION=(ADDRESS=(PROTOCOL=" + networkProtocol + ")(PORT=" + portNumber + ")(HOST=" + serverName + "))(CONNECT_DATA=(SERVICE_NAME=" + serviceName + ")))";
        } else
        {
            url = "jdbc:oracle:" + driverType + ":@(DESCRIPTION=(ADDRESS=(PROTOCOL=" + networkProtocol + ")(PORT=" + portNumber + ")(HOST=" + serverName + "))(CONNECT_DATA=(SID=" + databaseName + ")))";
            DatabaseError.addSqlWarning(null, new SQLWarning("URL with SID jdbc:subprotocol:@host:port:sid will be deprecated " +
"in 10i\nPlease use URL with SERVICE_NAME as jdbc:subprotocol:@//" +
"host:port/service_name"
));
            if(fastConnFailover)
                DatabaseError.throwSqlException(67, "OracleDataSource.makeURL");
        }
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.makeURL(): url=" + url, this);
            OracleLog.recursiveTrace = false;
        }
    }

    protected void trace(String s)
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.trace(" + s + "): logWriter=" + logWriter, this);
            OracleLog.recursiveTrace = false;
        }
        if(logWriter != null)
            OracleLog.print(this, 2, 2, 32, "OracleDataSource.trace(s): logWriter is not null");
    }

    protected void copy(OracleDataSource ds)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.copy(" + ds + ")", this);
            OracleLog.recursiveTrace = false;
        }
        ds.setUser(user);
        ds.setPassword(password);
        ds.setTNSEntryName(tnsEntry);
        makeURL();
        ds.setURL(url);
        ds.connectionProperties = connectionProperties;
    }

    /**
     * @deprecated Method setMaxStatements is deprecated
     */

    public void setMaxStatements(int max)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setMaxStatements(" + max + ")", this);
            OracleLog.recursiveTrace = false;
        }
        maxStatements = max;
    }

    public int getMaxStatements()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getMaxStatements(), returning " + maxStatements, this);
            OracleLog.recursiveTrace = false;
        }
        return maxStatements;
    }

    public void setImplicitCachingEnabled(boolean cache)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setImplicitCachingEnabled(" + cache + ")", this);
            OracleLog.recursiveTrace = false;
        }
        implicitCachingEnabled = cache;
    }

    public boolean getImplicitCachingEnabled()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getImplicitCachingEnabled(), returning " + implicitCachingEnabled, this);
            OracleLog.recursiveTrace = false;
        }
        return implicitCachingEnabled;
    }

    public void setExplicitCachingEnabled(boolean cache)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setExplicitCachingEnabled(" + cache + ")", this);
            OracleLog.recursiveTrace = false;
        }
        explicitCachingEnabled = cache;
    }

    public boolean getExplicitCachingEnabled()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getExplicitCachingEnabled(), returning " + explicitCachingEnabled, this);
            OracleLog.recursiveTrace = false;
        }
        return explicitCachingEnabled;
    }

    public void setConnectionProperties(Properties value)
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.setConnectionProperties (value=" + value + " )", this);
            OracleLog.recursiveTrace = false;
        }
        if(value == null)
            connectionProperties = value;
        else
            connectionProperties = (Properties)value.clone();
        setSpawnNewThreadToCancel(fastConnFailover);
    }

    public Properties getConnectionProperties()
        throws SQLException
    {
        if(TRACE && !OracleLog.recursiveTrace)
        {
            OracleLog.recursiveTrace = true;
            OracleLog.poolLogger.log(Level.FINE, "OracleDataSource.getConnectionProperties (), returning " + connectionProperties, this);
            OracleLog.recursiveTrace = false;
        }
        return filterConnectionProperties(connectionProperties);
    }

    public static final Properties filterConnectionProperties(Properties prop)
    {
        Properties result = null;
        if(prop != null)
        {
            result = (Properties)prop.clone();
            Enumeration enu = result.propertyNames();
            char c[] = null;
            do
            {
                if(!enu.hasMoreElements())
                    break;
                String key = (String)enu.nextElement();
                if(key != null && key.matches(".*[P,p][A,a][S,s][S,s][W,w][O,o][R,r][D,d].*"))
                    result.remove(key);
            } while(true);
            prop.remove("oracle.jdbc.spawnNewThreadToCancel");
        }
        return result;
    }

    private void setSpawnNewThreadToCancel(boolean enable)
    {
        if(enable)
        {
            if(connectionProperties == null)
                connectionProperties = new Properties();
            connectionProperties.setProperty("oracle.jdbc.spawnNewThreadToCancel", "true");
        } else
        if(connectionProperties != null)
            connectionProperties.remove("oracle.jdbc.spawnNewThreadToCancel");
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException, SQLException
    {
        in.defaultReadObject();
        if(connCachingEnabled)
            setConnectionCachingEnabled(connCachingEnabled);
    }

    protected PrintWriter logWriter;
    protected int loginTimeout, portNumber, maxStatements;
    protected String databaseName, serviceName, dataSourceName, description;
    protected String networkProtocol, user, password, serverName;
    protected String url, driverType, tnsEntry, connCacheName;
    protected boolean implicitCachingEnabled, explicitCachingEnabled, connCachingEnabled, fastConnFailover;
    protected transient OracleImplicitConnectionCache odsCache;
    protected transient OracleConnectionCacheManager cacheManager;
    protected Properties connCacheProperties, connectionProperties;
    protected String onsConfigStr;
    public boolean isOracleDataSource;
    private static final boolean fastConnectionFailoverSysProperty = "true".equalsIgnoreCase(OracleDriver.getSystemPropertyFastConnectionFailover("false"));
    private boolean urlExplicit, useDefaultConnection;
    protected transient OracleDriver driver;
    private static final String spawnNewThreadToCancelProperty = "oracle.jdbc.spawnNewThreadToCancel";
    private static final String _Copyright_2004_Oracle_All_Rights_Reserved_ = null;
    public static boolean TRACE = false;
    public static final boolean PRIVATE_TRACE = false;
    public static final String BUILD_DATE = "Sat_Feb__2_11:52:09_PST_2008";

    static 
    {
        try
        {
            TRACE = OracleLog.registerClassNameAndGetCurrentTraceSetting(Class.forName("oracle.jdbc.pool.OracleDataSource"));
        }
        catch(Exception e) { }
    }
}
