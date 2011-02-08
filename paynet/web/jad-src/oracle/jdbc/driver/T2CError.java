/* Decompiled through IntelliJad */
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packfields(3) packimports(3) splitstr(64) radix(10) lradix(10) 
// Source File Name:   T2CError.java

package oracle.jdbc.driver;


class T2CError
{

    T2CError()
    {
        m_errorMessage = new byte[1024];
        m_sqlState = new byte[6];
    }

    int m_errorNumber;
    byte m_errorMessage[], m_sqlState[];
}
