package service.user;

import java.util.Collection;

public class UserServiceImpl implements UserService {
    private static final String SELECT_USER_BY_ID = "select * from user where user.id = ?";
    private static final String CREATE_USERS = "INSERT INTO user (user.user_name, user.password, user.email" +
            "user.name, user.age, user.phone_number, user.identity_number, user.role" +
            "VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USERS_SQL = "update user set user.user_name = ?, user.password = ?, user.email = ?" +
            "user.name = ?, user.age = ?, user.phone_number = ?, user.identity_number = ?, user.role = ? where user.id = ?";
    private static final String DELETE_USERS_SQL = "delete from user where user.id = ?;";
    private static final String SELECT_USER = "";
    @Override
    public Collection showAll() {
        return null;
    }

    @Override
    public void create(Object object) {

    }

    @Override
    public Object showByIndex(int index) {
        return null;
    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void delete(int index) {

    }
}
