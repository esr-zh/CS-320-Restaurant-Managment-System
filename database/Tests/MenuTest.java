package database.Tests;

import database.Connect;
import database.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    Connection connection;
    Statement statement;

    public MenuTest() throws SQLException, ClassNotFoundException {
        Connect connect = new Connect();
        connection = connect.connection;
        statement = connect.statement;
    }

    @BeforeEach
    void setUp() throws SQLException {
        connection.setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    @Test
    void listAllMenu() throws SQLException {
        Menu menu = new Menu(connection);
        List<List<String>> result = menu.listAllMenu();

        assertTrue(result.get(0).get(1).contains("burger"));
        assertTrue(result.get(1).get(1).contains("USA"));
        assertTrue(result.get(2).get(1).contains("Tr"));

    }

    @Test
    void getMenuByName() throws SQLException, ClassNotFoundException {
        Menu menu = new Menu(connection);
        Menu menu_ = menu.getMenuByName("burger USA");
        assertEquals(1,menu_.getDishTypeId());
        assertEquals(165,menu_.getPrice());
        assertEquals(3,menu_.getId());
        assertEquals("burger from USA",menu_.getDescription());
    }

    @Test
    void createMenu() throws SQLException, ClassNotFoundException {
        Menu menu = new Menu(connection);
        menu.setName("penne");
        menu.setDescription("penne from dorm 4");
        menu.setPrice(55);
        menu.setDishTypeId(1);
        menu.setServingAmount(20);
        Menu createdMenu = menu.createMenu();

        assertEquals("penne", createdMenu.getName());
    }

    @Test
    void createMenuWithSameName() throws SQLException, ClassNotFoundException {
        Menu menu = new Menu(connection);
        menu.setName("burger");
        menu.setDescription("burger from dorm 4");
        menu.setPrice(65);
        menu.setDishTypeId(1);
        menu.setServingAmount(20);
        try {
            menu.createMenu();
        }catch (Exception e){
            assertEquals("menu item already exists", e.getMessage());
        }
    }

    @Test
    void deleteMenu() throws SQLException, ClassNotFoundException {
        Menu menu = new Menu(connection);
        menu.setId(5);
        assertTrue(menu.deleteMenu());
    }

    @Test
    void updateMenu() throws SQLException, ClassNotFoundException {
        Menu menu = new Menu(connection);
        menu.setId(1); // you passing existing id
        menu.setName("burger small");
        menu.setDescription("nice");
        assertTrue(menu.updateMenu());
        assertEquals(75.0,menu.getPrice());
    }
}