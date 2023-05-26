package service;

import model.Address;
import model.House;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/houserentingdb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "C0223g1@";

    public CustomerService() {
    }

    private static final String SELECT_FIVE_HOUSE = "select * from House join Review on House.house_id = Review.house_id order by rating desc limit 5";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<House> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler_plate code)
        List<House> houses = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FIVE_HOUSE);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int houseId = rs.getInt("houseId");
                Double price = rs.getDouble("price");
                LocalDate viewDate = rs.getDate("viewDate").toLocalDate();
                LocalDate unavailableUntil = rs.getDate("unavailableUntil").toLocalDate();
                String area = rs.getString("area");
                String type = rs.getString("type");
                Boolean status = rs.getBoolean("status");
                int addressId = rs.getInt("address_id");
                Address address = AddressManager.getAddressById(addressId);
                int renterId = rs.getInt("renterId");
                int ownerId = rs.getInt("ownerId");
                String description = rs.getString("description");
                houses.add(new House(houseId, price, viewDate, unavailableUntil,area,type,status,address,renterId,ownerId,description));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return houses;
    }

    private void printSQLException(SQLException e) {
    }
}
