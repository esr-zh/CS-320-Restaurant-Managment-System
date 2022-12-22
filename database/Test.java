package database;

import database.utils.Connect;

import java.sql.*;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connect connect = new Connect();
        Menu menu = new Menu(connect.connection);
        User user = new User(connect.connection);
        String username = "diamond3";
//         user.deleteUserUsername(username);
        System.out.println(user.deleteUserUsername(username));
    }
}
