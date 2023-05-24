package service;

import model.House;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CustomerService {

    private String jdbcURL = "jdbc:mysql://localhost:3306/houserentingdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "C0223g1@";


//    private static final String SELECT_HOUSE_BY_STATUS = "select house_id,price,unavaliable_until,area,discription from House where address =?";
    private static final String SELECT_HOUSE_BY_STATUS = "select * from house join address on address.address_id = house.address_id where province = ? and house.unavailable_until = ?;";

//    private static final String SELECT_HOUSE_ADDRESS = "select house_id,address_id,price,unavaliable_until,area,discription from House join Address on Address.address_id = House.address_id where address = ? and unavaliable_until = ?";

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


    public House selectHouse(int address , LocalDate unavailableUntil) {
        List<House> userList = new ArrayList<>();
        House house = null;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_HOUSE_BY_STATUS)) {
            statement.setInt(1, address);
            statement.setDate(2, java.sql.Date.valueOf(unavailableUntil));
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                double price = resultSet.getDouble("price");
                LocalDate viewDate = resultSet.getDate("view_date").toLocalDate();
                unavailableUntil = resultSet.getDate("unavailable_until").toLocalDate();
                String area = resultSet.getString("area");
                String type = resultSet.getString("type");
                Boolean status = resultSet.getBoolean("status");
                int addressId = resultSet.getInt("address_id");

                int renterId = resultSet.getInt("renter_id");
                int owner = resultSet.getInt("owner_id");
                String discription = resultSet.getString("discription");
                house = new House(houseId, price, viewDate, unavailableUntil, area, type, status, addressId, renterId, owner, discription);
                userList.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return house;
    }


}
