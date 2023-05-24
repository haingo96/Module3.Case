package service;

import com.mysql.jdbc.Driver;
import model.House;
import model.User;

import java.sql.*;
import java.util.List;

public class CustomerService implements CustomerInterface {

    private String jdbcURL = "jdbc:mysql://localhost:3306/houserentingdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "C0223g1@";


    private static final String SELECT_HOUSE_BY_STATUS = "select house_id,price,unavaliable_until,area,discription from House where status =?";

    private static final String SELECT_HOUSE_ADDRESS = "select house_id,address_id,price,unavaliable_until,area,discription from House join Address on Address.address_id = House.address_id where address = ? and unavaliable_until = ?";

    public CustomerService() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public void selectUser(boolean status) {
        House house = null;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_HOUSE_BY_STATUS)) {
            statement.setBoolean(1, status);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int house_id = resultSet.getInt("house_id");
                float price = resultSet.getFloat("price");
                Date unavaliable_until = resultSet.getDate("unavaliable_until");
                String area = resultSet.getString("area");
                String discription = resultSet.getString("discription");
                house = new House(house_id,price,unavaliable_until,area,discription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
