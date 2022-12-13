package database.Tests;

import database.utils.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTestable {
    Connection connection;
    Statement statement;

    public DBTestable() throws SQLException, ClassNotFoundException {
        Connect connect = new Connect();
        connection = connect.connection;
        statement = connect.statement;
    }
}
