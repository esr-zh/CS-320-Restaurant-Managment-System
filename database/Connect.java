package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connect {
    private static String url = "jdbc:mysql://localhost:3306/RMS";
    private static String username = "root";
    private static String password = "mhd-0559116452";
    public Statement statement;
    private static Connection connection;
    private static Connect instance;

    private Connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url,username,password);
        statement = connection.createStatement();
    }

    public static Connect getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new Connect();
        }
        return instance;
    }

    public void closeConnection() throws SQLException {
        this.statement.close();
        connection.close();
    }

    public static List<List<String>> returnArraylist(ResultSet rs) throws SQLException {
        ResultSetMetaData metadata = rs.getMetaData();
        int numcols = metadata.getColumnCount();
        List<List<String>> result = new ArrayList<>();

        while (rs.next()) {
            List<String> row = new ArrayList<>(numcols);
            int i = 1;
            while (i <= numcols) {
                row.add(rs.getString(i++));
            }
            result.add(row);
        }
        return result;
    }
}
