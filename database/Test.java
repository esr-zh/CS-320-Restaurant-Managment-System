package database;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try {
            Menu menu = Menu.createMenu(new Menu("burger USA", "burger from USA", 34, 165, 1));
            System.out.println(menu.getDescription());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
