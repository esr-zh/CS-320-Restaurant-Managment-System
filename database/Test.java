package database;
import com.mysql.cj.protocol.Resultset;
import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        System.out.println(User.authUser(new User("mhd","gh")));



    }
}
