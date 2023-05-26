package model;

public class User {
    private int id;
    private String name;
    private int age;
    private String email;
    private String phone_number;
    private String identity_number;
    private int role;
    private String user_name;
    private String password;

    public User(String user_name, String password, String email, String name, int age, String phone_number, String identity_number, int role) {
    }
    public User(int id,String user_name, String password, String email, String name, int age, String phone_number, String identity_number, int role) {
    }

    public User(int id, int role, String user_name, String password) {
        this.id = id;
        this.role = role;
        this.user_name = user_name;
        this.password = password;
    }

    public User(int id, String name, int age, String email, String phone_number, String identity_number, int role, String user_name, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone_number = phone_number;
        this.identity_number = identity_number;
        this.role = role;
        this.user_name = user_name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
