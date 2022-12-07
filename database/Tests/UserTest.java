package database.Tests;

import database.Connect;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.AssertJUnit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class UserTest {
    Connection connection;
    Statement statement;

    public UserTest() throws SQLException, ClassNotFoundException {
        Connect connect = new Connect();
        connection = connect.connection;
        statement = connect.statement;
    }

    @Before
    public void init() throws SQLException {
        connection.setAutoCommit(false);
    }

    @After
    public void rollBack() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }
    @Test
    public void createUserTest() throws SQLException, ClassNotFoundException {
        String username = "diamond3";
        database.User user = new database.User(connection);
        user.setUsername(username);
        user.setUserRole(1);
        user.setPassword("1234");
        user.createUser();
        assertEquals(username,user.getUsername());
    }

    @Test
    public void createUserTestWithSameUsername() throws SQLException, ClassNotFoundException {
        String username = "diamond2";
        database.User user = new database.User(connection);
        user.setUsername(username);
        user.setUserRole(1);
        user.setPassword("1234");
        try {
            user.createUser();
        }catch (Exception e){
            assertEquals("username is already taken!",e.getMessage());
        }
    }

    @Test
    public void authUser() throws SQLException, ClassNotFoundException {
        String username = "diamond2";
        database.User user = new database.User(connection);
        user.setUsername(username);
        user.setPassword("12345");
        user.authUser();
        assertEquals(4,user.getId());
        assertEquals(1,user.getUserRole());
    }

    @Test
    public void authUserWithWrongCredentials() throws SQLException, ClassNotFoundException {
        String username = "diamond2";
        database.User user = new database.User(connection);
        user.setUsername(username);
        user.setPassword("1234");
        try {
            user.authUser();
        }catch (Exception e){
            assertEquals("user not found!",e.getMessage());
        }
    }
}
