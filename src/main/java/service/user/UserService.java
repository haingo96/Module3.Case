package service.user;


import service.Connect;

import java.sql.Connection;
import java.util.Collection;

public interface UserService<E>{
    String jdbcURL = "jdbc:mysql://localhost:3306/houserentingdb";
    String jdbcUsername = "root";
    String jdbcPassword = "C0223g1@";
    Connect connect = new Connect(jdbcURL,jdbcUsername,jdbcPassword);
    Collection<E> showAll();

    void create(E object);

    E showByIndex(int index);

    void update(E object);

    void delete(int index);
}
