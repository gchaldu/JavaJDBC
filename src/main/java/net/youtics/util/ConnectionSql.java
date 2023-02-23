package net.youtics.util;

import java.sql.*;

public class ConnectionSql {

    private static String url = "jdbc:mysql://localhost:3306/java_curso";
    private static String user = "root";
    private static String password = "sasa";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {

            if(connection==null)
                connection = DriverManager.getConnection(url,user,password);

        return connection;
    }

}
