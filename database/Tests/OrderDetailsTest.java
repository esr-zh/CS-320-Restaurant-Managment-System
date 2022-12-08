package database.Tests;

import database.utils.Connect;

import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class OrderDetailsTest extends DBTestable{
    public OrderDetailsTest() throws SQLException, ClassNotFoundException {
       super();
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

}