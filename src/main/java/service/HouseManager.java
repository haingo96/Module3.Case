package service;

import model.Address;
import model.House;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HouseManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/houserentingdb";
    private static final String SQL_USERNAME = "root";
    private static final String SQL_PASSWORD = "C0223g1@";

    private static final String SQL_SELECT_ALL_HOUSE = "SELECT * FROM House;";
    private static final String SELECT_FIVE_HOUSE = "select * from House join Review on House.house_id = Review.house_id order by rating desc limit 5";


    public static Connection getConnection() {
        Connection connection;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, SQL_USERNAME, SQL_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static List<House> selectAllHouse() {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<House> houses = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_HOUSE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                double price = resultSet.getDouble("price");
                LocalDate viewDate = resultSet.getDate("view_date").toLocalDate();
                LocalDate unavailableUntil = resultSet.getDate("unavailable_until").toLocalDate();
                String area = resultSet.getString("area");
                String type = resultSet.getString("type");
                boolean status = resultSet.getBoolean("status");

                int addressId = resultSet.getInt("address_id");
                Address address = AddressManager.getAddressById(addressId);

                int renterId = resultSet.getInt("renter_id");
                int ownerId = resultSet.getInt("owner_id");
                String description = resultSet.getString("discription");

                House house = new House(houseId, price, viewDate, unavailableUntil, area, type, status, address, renterId, ownerId, description);
                houses.add(house);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return houses;
    }

    public static House display(int id) {
        House house = null;

        // using try-with-resources to avoid closing resources (boiler plate code)
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FIVE_HOUSE);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int houseId = rs.getInt("house_id");
                Double price = rs.getDouble("price");
                LocalDate viewDate = rs.getDate("view_date").toLocalDate();
                LocalDate unavailableUntil = rs.getDate("unavailable_until").toLocalDate();
                String area = rs.getString("area");
                String type = rs.getString("type");
                Boolean status = rs.getBoolean("status");
                int addressId = rs.getInt("address_id");
                Address address = AddressManager.getAddressById(addressId);
                int renterId = rs.getInt("renter_id");
                int ownerId = rs.getInt("owner_id");
                String description = rs.getString("discription");
                house = new House(houseId, price, viewDate, unavailableUntil,area,type,status,address,renterId,ownerId,description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return house;
    }

    public static void main(String[] args) {
        HouseManager houseManager = new HouseManager();
        House house = houseManager.display(3);
        System.out.println(house);
    }
}
