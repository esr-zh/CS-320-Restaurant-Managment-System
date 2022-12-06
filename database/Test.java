package database;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        TransactionHistory.createTransactionHistory(new TransactionHistory(3,false,0));
        System.out.println(TransactionHistory.getAddToCartTableByUserId(2));
    }
}
