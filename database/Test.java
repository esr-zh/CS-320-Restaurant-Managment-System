package database;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        UserRole ur = new UserRole();

        System.out.println(ur.getUserRole(3));



    }
}
