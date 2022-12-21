package database;

import database.utils.Connect;

import java.sql.*;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connect connect = new Connect();
        TransactionHistory TH = new TransactionHistory(connect.connection);
        List<List<String>> result = TH.getAddToCartTableByUserId(4);
        System.out.println(result);
    }
}
