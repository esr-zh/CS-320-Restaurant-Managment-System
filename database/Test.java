package database;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Menu menu = Menu.createMenu(new Menu(1,"burger Tr", "nice burger from Turkey", 30, 75,1));
        System.out.println(Menu.listAllMenu());
    }
}
