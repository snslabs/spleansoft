package com.markit.dtccrelay.mtp;

import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.math.BigDecimal;
import java.math.BigInteger;

public class DbTest extends TestCase {
    private Connection conn;

    {
        try {
            DriverManager.registerDriver(new com.sybase.jdbc2.jdbc.SybDriver());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }


    protected void setUp() throws Exception {
        conn = DriverManager.getConnection("jdbc:sybase:Tds:172.30.47.78:7000/STQIBAPP", "stqibapp", "stqibapp");
        conn.setAutoCommit(false);
    }

    public void testBigDecimal() throws Exception{
        testBigDecimal0(new BigDecimal(600000), new BigDecimal(589000));
        testBigDecimal0(new BigDecimal(new BigInteger("6"),-5), new BigDecimal("590000"));
    }

    private void testBigDecimal0(BigDecimal notionalAmount, BigDecimal netAmount) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" insert ISDATicketTradedAccounts (TICKETID, AccountId, NotionalAmount, NetAmount) values " +
                    "(?,?,?,?)");
            Long ticketId = 123123123L;
            String accountId = "test-account-id-2";

            ps.setLong(1,ticketId);
            ps.setString(2, accountId);
            ps.setBigDecimal(3, notionalAmount);
            ps.setBigDecimal(4, netAmount);


            System.out.println(notionalAmount + ", "+ netAmount);

            ps.execute();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (ps != null) {
                ps.close();
            }
        }

    }


    protected void tearDown() throws Exception {
        conn.rollback();
        conn.close();
    }
}
