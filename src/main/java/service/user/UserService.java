package service.user;


import model.User;
import service.Connect;

import java.sql.Connection;
import java.util.Collection;

public interface UserService<E>{
    String jdbcURL = "jdbc:mysql://localhost:3306/houserentingdb";
    String jdbcUsername = "root";
    String jdbcPassword = "C0223g1@";
    Connect connect = new Connect(jdbcURL,jdbcUsername,jdbcPassword);
    Collection<E> showAll();

    void create(User user);

    E showByIndex(int index);

    void update(E object);

    void delete(int index);
    User login(String user_name, String password);
    User Sign_up(String user_name, String password, int role_id);

}
