package Tests;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;

public abstract class Test
{
    static Connection conn;
    static void connect()
    {
        try
        {
            conn = ConnectionEstablisher.establish();
        }
        catch (SQLException e)
        {
            System.out.println("NIE UDALO SIE POLACZYC");
            e.printStackTrace();
        }
    }
    static void disconnect()
    {
        ConnectionEstablisher.disconnect(conn);
    }
    abstract boolean performTest();
}
