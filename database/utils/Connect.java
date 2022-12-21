package database.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connect {
    private static String url = "jdbc:mysql://us-east.connect.psdb.cloud";
    private static String username = "pfq1o3esw8ppep0oklod";
    private static String password = "pscale_pw_v869xGcV5DMV189VtTt4gbJvb11fyHjsJxc9Y13AgNP";
    public Statement statement;
    public Connection connection;
    private static Connect instance;

    public Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
