package database;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        User user = User.createUser(new User("mhd", "123", 1));

        System.out.println(user.getUserRole());



    }
}
