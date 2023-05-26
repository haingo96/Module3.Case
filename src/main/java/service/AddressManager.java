package service;

import model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressManager {
    private static String SQL_SELECT_ADDRESS_BY_ID = "SELECT * FROM Address WHERE address_id = ?;";

    private static String SQL_INSERT_NEW_ADDRESS = "INSERT INTO Address VALUES (?, ?, ?, NULL);";

    private static String SQL_SELECT_ID_BY_FIELDS = "SELECT address_id FROM Address WHERE province = ? AND district = ? AND ward = ?;";

    public static Address getAddressById(int id) {
        Connection connection = HouseManager.getConnection();
        Address address;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ADDRESS_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            String province = "";
            String district = "";
            String ward = "";
            while (resultSet.next()) {
                province = resultSet.getString("province");
                district = resultSet.getString("district");
                ward = resultSet.getString("ward");
            }

            address = new Address(province, district, ward);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return address;
    }

    public static void insertNewAddress(Address address) {
        Connection connection = HouseManager.getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_ADDRESS);
            preparedStatement.setString(1, address.getProvince());
            preparedStatement.setString(2, address.getDistrict());
            preparedStatement.setString(3, address.getWard());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000") && e.getMessage().contains("Duplicate entry")) {
                // handle duplicate entry exception here
                System.out.println("Value already exists in unique column.");
            } else {
                // handle other SQL exceptions here
                e.printStackTrace();
            }
        }
    }

    public static int findAddressId(Address address) {
        Connection connection = HouseManager.getConnection();
        PreparedStatement preparedStatement;
        int id = 0;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ID_BY_FIELDS);
            preparedStatement.setString(1, address.getProvince());
            preparedStatement.setString(2, address.getDistrict());
            preparedStatement.setString(3, address.getWard());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("address_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }
}
