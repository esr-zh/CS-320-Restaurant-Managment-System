package database;

import database.utils.Connect;

import java.sql.*;

import static database.utils.Salter.salt;

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
        this.id = id;
        this.username = username;
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

    public User getUserById(long id) throws SQLException {

        String SQL_INSERT = "SELECT * from user WHERE user.id = ?";
        PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        if (!rs.next()){
            throw new SQLException("user id not found!");
        }

        return new User(id,rs.getString(2), Long.parseLong(rs.getString(4)));
    }

    public User getUserByUsername(String username) throws SQLException, ClassNotFoundException {
        String SQL_INSERT = "SELECT * from user where user.username = ?";
        PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        if (!rs.next()){
            throw new SQLException("user id not found!");
        }
//        System.out.println(rs.getString(1));// id
//        System.out.println(rs.getString(2));// username
//        System.out.println(rs.getString(4));// role id
        return new User(Integer.parseInt(rs.getString(1)),
                rs.getString(2),
                Integer.parseInt(rs.getString(4)));
    }

    public Boolean updateUser() throws SQLException { // only updates username and user role

        String SQL_QUERY = "UPDATE user SET username = ? user_role = ? WHERE user.id = ?";
        User currentUser = getUserById(id);
        try (PreparedStatement statement = conn.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            if (!username.equals(currentUser.getUsername())) {
                setUsername(username);
            }else {
                setUsername(currentUser.getUsername());
            }
            if (userRole != currentUser.getUserRole()){
                setUserRole(userRole);
            }else {
                setUserRole(currentUser.getUserRole());
            }

            statement.setString(1,username);
            statement.setLong(2,userRole);
            statement.setLong(3,id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("deleting menu failed, no rows affected.");
            }

            return true;
        }


    }

    public Boolean doesUserExists(String username) throws SQLException {
        String SQL_QUERY = "SELECT * from user where user.username = ?";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setString(1,username);
        ResultSet resultSet = statement.executeQuery();
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

    public boolean deleteUserUsername(String username) throws SQLException {
        String SQL_INSERT = "DELETE FROM user WHERE user.username = ?";
        try (
                PreparedStatement statement = conn.prepareStatement(SQL_INSERT,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1,username);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            return true;
        }
    }
}
