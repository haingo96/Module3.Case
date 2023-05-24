package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomerService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/houserentingdb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "C0223g1";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
}
