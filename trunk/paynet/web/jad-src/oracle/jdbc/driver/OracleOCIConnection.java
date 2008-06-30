/* Decompiled through IntelliJad */
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packfields(3) packimports(3) splitstr(64) radix(10) lradix(10) 
// Source File Name:   OracleOCIConnection.java

package oracle.jdbc.driver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.pool.OracleOCIConnectionPool;

// Referenced classes of package oracle.jdbc.driver:
//            T2CConnection, OracleDriverExtension, OracleLog

public abstract class OracleOCIConnection extends T2CConnection
{

    public OracleOCIConnection(String ur, String us, String p, String db, Properties info, Object ext)
        throws SQLException
    {
        this(ur, us, p, db, info, (OracleDriverExtension)ext);
    }

    OracleOCIConnection(String ur, String us, String p, String db, Properties info, OracleDriverExtension ext)
        throws SQLException
    {
        super(ur, us, p, db, info, ext);
        ociConnectionPool = null;
        isPool = false;
        aliasing = false;
    }

    public synchronized byte[] getConnectionId()
        throws SQLException
    {
        byte connId[] = t2cGetConnectionId(m_nativeState);
        aliasing = true;
        return connId;
    }

    public synchronized void passwordChange(String user, String oldPassword, String newPassword)
        throws SQLException, IOException
    {
        ociPasswordChange(user, oldPassword, newPassword);
    }

    public synchronized void close()
        throws SQLException
    {
        if(lifecycle == 2 || lifecycle == 4 || aliasing)
        {
            return;
        } else
        {
            super.close();
            ociConnectionPool.connectionClosed((oracle.jdbc.oci.OracleOCIConnection)this);
            return;
        }
    }

    public synchronized void setConnectionPool(OracleOCIConnectionPool cpool)
    {
        ociConnectionPool = cpool;
    }

    public synchronized void setStmtCacheSize(int size, boolean clearMetaData)
        throws SQLException
    {
        super.setStmtCacheSize(size, clearMetaData);
    }

    OracleOCIConnectionPool ociConnectionPool;
    boolean isPool, aliasing;
    private static final String _Copyright_2004_Oracle_All_Rights_Reserved_ = null;
    public static boolean TRACE = false;
    public static final boolean PRIVATE_TRACE = false;
    public static final String BUILD_DATE = "Sat_Feb__2_11:52:05_PST_2008";

    static 
    {
        try
        {
            TRACE = OracleLog.registerClassNameAndGetCurrentTraceSetting(Class.forName("oracle.jdbc.driver.OracleOCIConnection"));
        }
        catch(Exception e) { }
    }
}
