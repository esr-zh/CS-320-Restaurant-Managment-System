package database.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Helper{
    public static <T extends Template> T executeAndGetId(T object, PreparedStatement statement) throws SQLException {
        T new_object = (T) object.clone();// to make function pure
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating transaction history failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                new_object.setId(generatedKeys.getLong(1));
                return new_object;
            } else {
                throw new SQLException("Creating transaction history failed, no ID obtained.");
            }
        }
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
