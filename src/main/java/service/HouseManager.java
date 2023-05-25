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
}
