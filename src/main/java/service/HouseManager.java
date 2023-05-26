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

    private static final String SQL_INSERT_NEW_HOUSE = "INSERT INTO House VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, NULL, ?, ?);";

    private static final String SQL_DEL_HOUSE_BY_ID = "DELETE FROM House WHERE house_id = ?;";

    private static final String SQL_SELECT_HOUSE_BY_ID = "SELECT * FROM House WHERE house_id = ?;";

    private static final String SQL_UPDATE_HOUSE_BY_ID = "UPDATE House SET price = ?, area = ?, type = ?, address_id = ?, discription = ? WHERE house_id = ?;";

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

//                LocalDate viewDate = resultSet.getDate("view_date").toLocalDate();
//                LocalDate unavailableUntil = resultSet.getDate("unavaliable_until").toLocalDate();

                Date date = resultSet.getDate("view_date");
                LocalDate viewDate = date == null ? null : date.toLocalDate();

                Date date1 = resultSet.getDate("unavailable_until");
                LocalDate unavailableUntil = date == null ? null : date1.toLocalDate();

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
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_HOUSE);) {
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
                house = new House(houseId, price, viewDate, unavailableUntil, area, type, status, address, renterId, ownerId, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return house;
    }

    public static void insertNewHouse(House house) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_HOUSE);
            preparedStatement.setDouble(1, house.getPrice());
            preparedStatement.setDate(2, null);
            preparedStatement.setDate(3, null);
            preparedStatement.setString(4, house.getArea());
            preparedStatement.setString(5, house.getType());
            preparedStatement.setBoolean(6, false);

            Address address = new Address(house.getAddress().getProvince(), house.getAddress().getDistrict(), house.getAddress().getWard());
            AddressManager.insertNewAddress(address);
            int addressId = AddressManager.findAddressId(address);
            preparedStatement.setInt(7, addressId);
            preparedStatement.setInt(8, house.getOwnerId());
            preparedStatement.setString(9, house.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteHouseById(int id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL_DEL_HOUSE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static House getHouseById(int id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        House house = new House();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_HOUSE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                double price = resultSet.getDouble("price");


                Date date = resultSet.getDate("view_date");
                LocalDate viewDate = date == null ? null : date.toLocalDate();

                Date date1 = resultSet.getDate("unavailable_until");
                LocalDate unavailableUntil = date == null ? null : date1.toLocalDate();

                String area = resultSet.getString("area");
                String type = resultSet.getString("type");
                boolean status = resultSet.getBoolean("status");

                int addressId = resultSet.getInt("address_id");
                Address address = AddressManager.getAddressById(addressId);

                int renterId = resultSet.getInt("renter_id");
                int ownerId = resultSet.getInt("owner_id");
                String description = resultSet.getString("discription");

                house = new House(houseId, price, viewDate, unavailableUntil, area, type, status, address, renterId, ownerId, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return house;
    }

    public static void updateHouseById(House house) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_HOUSE_BY_ID);
            preparedStatement.setDouble(1, house.getPrice());
            preparedStatement.setString(2, house.getArea());
            preparedStatement.setString(3, house.getType());

            Address address = new Address(house.getAddress().getProvince(), house.getAddress().getDistrict(), house.getAddress().getWard());
            AddressManager.insertNewAddress(address);
            int addressId = AddressManager.findAddressId(address);
            preparedStatement.setInt(4, addressId);

            preparedStatement.setString(5, house.getDescription());
            preparedStatement.setInt(6, house.getHouseId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}