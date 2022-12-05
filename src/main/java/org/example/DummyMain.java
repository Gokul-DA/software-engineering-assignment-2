package org.example;

import java.sql.*;

public class DummyMain {
    public static void main(String[] args) {
        String url_SQLServer = "jdbc:sqlserver://localhost:1433;database=software_engineering;user=common;password=common";
        String queryDetails = "select ownerPassword from petstore.owner where ownerId = 1";
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con_SQLServer = DriverManager.getConnection(url_SQLServer);
            Statement stmt = con_SQLServer.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(queryDetails);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (NullPointerException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
