package service;

import model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressManager {
    private static String SQL_SELECT_ADDRESS_BY_ID = "SELECT * FROM Address WHERE address_id = ?;";

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
}
