package database;
import com.mysql.cj.protocol.Resultset;
import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try {
            User r = User.createUser(new User("nhah","12345", 3));
            System.out.println(r.getId());
        }catch (Exception e){

            System.out.println(e.getMessage());
        }



    }
}
