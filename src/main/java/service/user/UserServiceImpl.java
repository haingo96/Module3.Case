package service.user;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class UserServiceImpl implements UserService<User> {
    private static final String SELECT_USER_BY_ID = "select * from user where user_id = ?";
    private static final String CREATE_USERS = "INSERT INTO user (user.user_name, user.password, user.email" +
            "user.name, user.age, user.phone_number, user.identity_number, role_id" +
            "VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USERS_SQL = "update user set user.user_name = ?, user.password = ?, user.email = ?" +
            "user.name = ?, user.age = ?, user.phone_number = ?, user.identity_number = ?, role_id = ? where user_id = ?";
    private static final String DELETE_USERS_SQL = "delete from user where user_id = ?;";
    private static final String SELECT_USER = "select * from user where user_name=? and password = ? and role_id = ?";

    public UserServiceImpl() {

    }


    @Override
    public Collection<User> showAll() {
        return null;
    }

    @Override
    public void create(User user) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USERS)) {
            preparedStatement.setString(1, user.getUser_name());
            preparedStatement.setString(2, user.getPassword());
            if (user.getEmail() != "") {
                preparedStatement.setString(3, user.getEmail());
            } else {
                preparedStatement.setString(3, null);
            }
            if (user.getName() != "") {
                preparedStatement.setString(4, user.getName());
            } else {
                preparedStatement.setString(4, null);
            }
            preparedStatement.setInt(5, user.getAge());
            if (user.getPhone_number() != "") {
                preparedStatement.setString(6, user.getPhone_number());
            } else {
                preparedStatement.setString(6, null);
            }
            if (user.getIdentity_number() != "") {
                preparedStatement.setString(7, user.getIdentity_number());
            } else {
                preparedStatement.setString(7, null);
            }
            if (user.getRole() == 1) {
                preparedStatement.setInt(8, user.getRole());
            } else if (user.getRole() != 1) {
                System.out.println("ko the dang kí tai khoan chu cho thue vui long nhap 1 o buoc role de dang ki");
            } else if (user.getRole() == 2) {
                preparedStatement.setInt(8, user.getRole());
            } else if (user.getRole() != 2) {
                System.out.println("ko the dang ki tai khoan khach vui long nhap 2 o buoc chon role de dang ki");
            } else {
                System.out.println("vui long nhap role 1 chu cho thue 2 khach");
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User showByIndex(int index) {
        User user = null;
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, index);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String user_name = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String phone_number = resultSet.getString("phone_number");
                String identity_number = resultSet.getString("identity_number");
                Integer role = resultSet.getInt("role");
                Integer id = resultSet.getInt("id");
                user = new User(id, name, age, email, phone_number, identity_number, role, user_name, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, user.getUser_name());
            preparedStatement.setString(2, user.getPassword());
            if (user.getEmail() != "") {
                preparedStatement.setString(3, user.getEmail());
            } else {
                preparedStatement.setString(3, null);
            }
            if (user.getName() != "") {
                preparedStatement.setString(4, user.getName());
            } else {
                preparedStatement.setString(4, null);
            }
            preparedStatement.setInt(5, user.getAge());

            if (user.getPhone_number() != "") {
                preparedStatement.setString(6, user.getPhone_number());
            } else {
                preparedStatement.setString(6, null);
            }
            if (user.getIdentity_number() != "") {
                preparedStatement.setString(7, user.getIdentity_number());
            } else {
                preparedStatement.setString(7, null);
            }
            if (user.getRole() == 1) {
                preparedStatement.setInt(8, user.getRole());
            } else if (user.getRole() != 1) {
                System.out.println("ko the dang kí tai khoan chu cho thue vui long nhap 1 o buoc role de dang ki");
            } else if (user.getRole() == 2) {
                preparedStatement.setInt(8, user.getRole());
            } else if (user.getRole() != 2) {
                System.out.println("ko the dang ki tai khoan khach vui long nhap 2 o buoc chon role de dang ki");
            } else {
                System.out.println("vui long nhap role 1 chu cho thue 2 khach");
            }
            preparedStatement.setInt(9, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL)) {
            preparedStatement.setInt(1, index);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User login(String user_name, String password) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);) {
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 2);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String phone_number = resultSet.getString("phone_number");
                String identity_number = resultSet.getString("identity_number");
                String email = resultSet.getString("email");
                int role = resultSet.getInt("role_id");
                String username = resultSet.getString("user_name");
                String pass = resultSet.getString("password");


                User u = new User(id, name, age, phone_number, identity_number, email, role, username, pass);
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User Sign_up(String user_name, String password, int role_id) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER)) {
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, role_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
