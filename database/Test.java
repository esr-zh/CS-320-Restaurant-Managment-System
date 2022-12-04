package database;
import com.mysql.cj.protocol.Resultset;
import com.mysql.jdbc.Driver;
import database.Tests.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        UserRole ur = new UserRole();

        System.out.println(ur.getUserRole(3));



    }
}
