package database.Tests;

import database.Connect;
import database.User;
import java.io.FileInputStream;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class OrderDetailsTest {
    Connection connection;
    Statement statement;

    public OrderDetailsTest() throws SQLException, ClassNotFoundException {
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

}