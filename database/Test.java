package database;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        boolean isAuth = User.authUser(new User("mhd", "lol", 1));

        System.out.println(isAuth);



    }
}
