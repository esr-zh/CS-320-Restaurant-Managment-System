package database.utils;

import database.Template;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
