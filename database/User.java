package database;

import java.sql.*;

import static database.Salter.salt;

public class User {

    private Connection conn;

    private long id;
    private String username;
    private String password;
    private long userRole;

    public User(Connection conn) {
        this.conn = conn;
    }

    public User(String username, String password, long user_role) {
        this.username = username;
        this.password = password;
        this.userRole = user_role;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(long id, String username, long user_role) {
        this.username = username;
        this.id = id;
        this.userRole = user_role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserRole() {
        return userRole;
    }

    public void setUserRole(long userRole) {
        this.userRole = userRole;
    }

    public  User createUser() throws SQLException, ClassNotFoundException {
        String SQL_INSERT = "INSERT INTO user(username,password,user_role) VALUES (?, ?, ?)";
//        Connect connect = Connect.getInstance();
        if (!doesUserExists(username)) {
            try (
                    PreparedStatement statement = conn.prepareStatement(SQL_INSERT,
                            Statement.RETURN_GENERATED_KEYS);
            ) {
                statement.setString(1, username);
                statement.setString(2, salt(password, "never_hack_me"));
                statement.setString(3, String.valueOf(userRole));

                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
            return this;
        }
        throw new SQLException("username is already taken!");
    }

    public User authUser() throws SQLException{
        String SQL_INSERT = "SELECT * FROM user WHERE username = ? AND password = ?";
//        Connect connect = Connect.getInstance();
        PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
        statement.setString(1, username);
        statement.setString(2, salt(password, "never_hack_me"));

        ResultSet rs = statement.executeQuery();

        if (!rs.next())
            throw new SQLException("user not found!");
        setId(rs.getLong(1));
        setUserRole(rs.getLong(4));
        return this;
    }

    public static User getUserById(long id) throws SQLException, ClassNotFoundException {
        Connect connect = Connect.getInstance();
        ResultSet resultSet = connect.statement.executeQuery(
                String.format("SELECT * from user where user.id = %d",id));
        if (!resultSet.next()){
            throw new SQLException("user id not found!");
        }
        User return_user = new User(id,resultSet.getString(2), Long.parseLong(resultSet.getString(4)));
        connect.closeConnection();
        return return_user;
    }

    public static User getUserByUsername(String username) throws SQLException, ClassNotFoundException {
        Connect connect = Connect.getInstance();
        ResultSet resultSet = connect.statement.executeQuery(
                String.format("SELECT * from user where user.username = '%s'",username));
        if (!resultSet.next()){
            throw new SQLException("username id not found!");
        }
        User return_user = new User(resultSet.getString(1),resultSet.getString(2),
                Long.parseLong(resultSet.getString(4)));
//        connect.closeConnection();
        return return_user;
    }

    public static Boolean doesUserExists(String username) throws SQLException, ClassNotFoundException {
        Connect connect = Connect.getInstance();
        ResultSet resultSet = connect.statement.executeQuery(
                String.format("SELECT * from user where user.username = '%s'",username));
        return resultSet.next();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_role=" + userRole +
                '}';
    }
}
