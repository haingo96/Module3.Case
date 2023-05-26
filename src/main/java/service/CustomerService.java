package service;

import model.Address;
import model.House;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements CustomerInterface {

    private String jdbcURL = "jdbc:mysql://localhost:3306/houserentingdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "C0223g1@";


    private static final String SELECT_HOUSE_ALL = "select * from House ";
    private static final String SELECT_HOUSE_BY_STATUS = "select * from House where status = ? and unavailable_until = ? ";


    private static final String SELECT_HOUSE_BY_TEST = " SELECT house.house_id, house.price,house.view_date,house.unavailable_until, house.area, house.type, house.status,house.address_id,house.renter_id,house.owner_id,house.discription, address.province, address.district, address.ward FROM house JOIN address ON address.address_id = house.address_id WHERE address.province = ? ";
    //    private static final String SELECT_HOUSE_BY_ID = "select * from House where unavailable_until = ? ";
    private static final String SELECT_HOUSE_BY_DATE = "select * from House where house_id = ? ";

    private static final String SELECT_HOUSE_ADDRESS = "select house_id ,price,unavailable_until,area,type,status, address.province , address.district , address.ward from house \n" +
            "join address on address.address_id = house.address_id;";

    private static final String SELECT_FIVE_HOUSE = "select * from House join Review on House.house_id = Review.house_id order by rating desc limit 5";

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

    public List<House> findAll() {
        List<House> houseList = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOUSE_ALL)) {


            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                double price = resultSet.getDouble("price");
                LocalDate viewDate = resultSet.getDate("view_date").toLocalDate();
                LocalDate unavailableUntil = resultSet.getDate("unavailable_until").toLocalDate();
                String area = resultSet.getString("area");
                String type = resultSet.getString("type");
                boolean status = resultSet.getBoolean("status");
                int addressId = resultSet.getInt("address_id");
                Address address1 = AddressManager.getAddressById(addressId);
                int renterId = resultSet.getInt("renter_id");
                int owner = resultSet.getInt("owner_id");
                String discription = resultSet.getString("discription");
                houseList.add(new House(houseId, price, viewDate, unavailableUntil, area, type, status, address1, renterId, owner, discription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houseList;
    }

    public List<House> findIndexAddress(String address) {
        List<House> houseList = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOUSE_BY_TEST)) {
            preparedStatement.setString(1, address);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                double price = resultSet.getDouble("price");
                LocalDate viewDate = resultSet.getDate("view_date").toLocalDate();
                LocalDate unavailableUntil = resultSet.getDate("unavailable_until").toLocalDate();
                String area = resultSet.getString("area");
                String type = resultSet.getString("type");
                Boolean status = resultSet.getBoolean("status");
                int addressId = resultSet.getInt("address_id");
                String addressIdStr = String.valueOf(addressId);
                Address address1 = AddressManager.getAddressById(Integer.parseInt(addressIdStr));
                int renterId = resultSet.getInt("renter_id");
                int owner = resultSet.getInt("owner_id");
                String discription = resultSet.getString("discription");
                houseList.add(new House(houseId, price, viewDate, unavailableUntil, area, type, status, address1, renterId, owner, discription));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return houseList;
    }

    @Override
    public List<House> findIndexID(int status1, LocalDate unavailable_until) {
        List<House> houseList1 = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOUSE_BY_STATUS)) {
            preparedStatement.setInt(1, status1);
            preparedStatement.setDate(2, Date.valueOf(unavailable_until));
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                double price = resultSet.getDouble("price");
                LocalDate viewDate = resultSet.getDate("view_date").toLocalDate();
                unavailable_until = resultSet.getDate("unavailable_until").toLocalDate();
                String area = resultSet.getString("area");
                String type = resultSet.getString("type");
                Boolean status = resultSet.getBoolean("status");
                int addressId = resultSet.getInt("address_id");
                String addressIdStr = String.valueOf(addressId);
                Address address1 = AddressManager.getAddressById(Integer.parseInt(addressIdStr));
                int renterId = resultSet.getInt("renter_id");
                int owner = resultSet.getInt("owner_id");
                String discription = resultSet.getString("discription");
                houseList1.add(new House(houseId, price, viewDate, unavailable_until, area, type, status, address1, renterId, owner, discription));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return houseList1;
    }

    public List<House> findIndex(int address) {
        List<House> houseList = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOUSE_BY_STATUS)) {
            preparedStatement.setInt(1, address);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                double price = resultSet.getDouble("price");
                LocalDate viewDate = resultSet.getDate("view_date").toLocalDate();
                LocalDate unavailableUntil = resultSet.getDate("unavailable_until").toLocalDate();
                String area = resultSet.getString("area");
                String type = resultSet.getString("type");
                Boolean status = resultSet.getBoolean("status");
                int addressId = resultSet.getInt("address_id");
                String addressIdStr = String.valueOf(addressId);
                Address address1 = AddressManager.getAddressById(Integer.parseInt(addressIdStr));
                int renterId = resultSet.getInt("renter_id");
                int owner = resultSet.getInt("owner_id");
                String discription = resultSet.getString("discription");
                houseList.add(new House(houseId, price, viewDate, unavailableUntil, area, type, status, address1, renterId, owner, discription));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return houseList;
    }
    public List<House> selectAllUsers() {
        // using try-with-resources to avoid closing resources (boiler plate code)
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
                houses.add(new House(houseId, price, viewDate, unavailableUntil, area, type, status, address, renterId, ownerId, description));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}





