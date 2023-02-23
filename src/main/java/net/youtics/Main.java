package net.youtics;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java_curso";
        String adm = "root";
        String pass = "sasa";

        try {
            Connection connection = DriverManager.getConnection(url, adm, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from productos");
            while (resultSet.next())
            {
                System.out.println("resultSet.getString(\"nombre\") = " + resultSet.getString("nombre"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}