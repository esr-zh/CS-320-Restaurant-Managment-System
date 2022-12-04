package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private long id;
    private String username;
    private String password;
    private long user_role;

    public User(String username, String password, int user_role) {
        this.username = username;
        this.password = password;
        this.user_role = user_role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUser_role() {
        return user_role;
    }

    public void setUser_role(long user_role) {
        this.user_role = user_role;
    }

    public static User createUser(User user) throws SQLException, ClassNotFoundException {
        Connect connect = Connect.getInstance();
        connect.statement.executeUpdate("INSERT INTO user(user.username,user.password,user.user_role) " + "VALUES (user.username, user.password, user.user_role)");
        connect.closeConnection();
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_role=" + user_role +
                '}';
    }
}
