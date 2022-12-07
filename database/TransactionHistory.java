package database;

import java.sql.*;
import java.util.List;

public class TransactionHistory {
    private long id;
    private long userId;
    private long createdAt;
    private boolean hasPaid;
    private long paidAt;

    public TransactionHistory() {

    }

    public long getId() {
        return id;
    }

    public TransactionHistory(long userId, boolean hasPaid, long paidAt) {
        this.userId = userId;
        this.hasPaid = hasPaid;
        this.paidAt = paidAt;
    }

    public TransactionHistory(long id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public long getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(long paidAt) {
        this.paidAt = paidAt;
    }

    // list transaction history by user id
    public static List<List<String>> getTransactionHistoryByUserId(int userId) throws SQLException, ClassNotFoundException {

        String SQL_QUERY = "SELECT * FROM transaction_history WHERE transaction_history.user_id = ? AND transaction_history.has_paid = true";
        return getLists(userId, SQL_QUERY);
    }

    // get add to card table
    public static List<List<String>> getAddToCartTableByUserId(int userId) throws SQLException, ClassNotFoundException {

        String SQL_QUERY = "SELECT * FROM transaction_history WHERE transaction_history.user_id = ? AND transaction_history.has_paid = false";
        return getLists(userId, SQL_QUERY);
    }

    // list transaction history by user id
    public static TransactionHistory updatePaidStatus(TransactionHistory TH) throws SQLException, ClassNotFoundException {
        Connect connect = Connect.getInstance();
        String SQL_QUERY = "UPDATE transaction_history SET has_paid = ?,paid_at = ?  OR transaction_history.id = ? AND transaction_history.user_id = ?";
        if (!doesUserPaidAll(TH.userId)){
            try (
                    PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY,
                            Statement.RETURN_GENERATED_KEYS);
            ) {
                long currentDate = new java.util.Date().getTime();
                statement.setLong(1, 1);
                statement.setLong(2, currentDate);
                statement.setLong(3, TH.id);
                statement.setLong(4, TH.userId);

                execute(TH, statement);
            }
            return TH;
        }

        throw new SQLException("user should not create any new history if the user didn't pay the previous one");

    }

    private static void execute(TransactionHistory TH, PreparedStatement statement) throws SQLException {
        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating transaction history failed, no rows affected.");
        }

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                TH.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating transaction history failed, no ID obtained.");
            }
        }
    }

    private static List<List<String>> getLists(int userId, String SQL_QUERY) throws SQLException, ClassNotFoundException {
        Connect connect = Connect.getInstance();
        PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY);
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();
        return Connect.returnArraylist(rs);
    }



    // create transaction history
    // user should not create any new history if the user didn't pay the previous one
    public static TransactionHistory createTransactionHistory(TransactionHistory TH) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "INSERT INTO transaction_history(user_id,created_at,has_paid,paid_at) VALUES (?, ?, ?, ?)";
        Connect connect = Connect.getInstance();
        if (!doesUserPaidAll(TH.userId)){
            try (
                    PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY,
                            Statement.RETURN_GENERATED_KEYS);
            ) {
                long currentDate = new java.util.Date().getTime();
                statement.setLong(1, TH.userId);
                statement.setLong(2, currentDate);
                statement.setLong(3, TH.hasPaid ? 1 : 0);
                statement.setLong(4, TH.paidAt);

                execute(TH, statement);
            }
            return TH;
        }

        throw new SQLException("user should not create any new history if the user didn't pay the previous one");

    }

    private static boolean doesUserPaidAll(long userId) throws SQLException, ClassNotFoundException {
        if (!doesUserHasTH(userId))// check if the user has any TH
            return false;

        String SQL_QUERY = "SELECT * FROM transaction_history WHERE " +
                    "transaction_history.user_id = ? AND transaction_history.has_paid = false;";
        Connect connect = Connect.getInstance();
        PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY);
        statement.setLong(1, userId);
        ResultSet rs = statement.executeQuery();
        return rs.next();

    }

    private static boolean doesUserHasTH(long userId) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "SELECT * FROM transaction_history WHERE " +
                "transaction_history.user_id = ?";
        Connect connect = Connect.getInstance();
        PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY);
        statement.setLong(1, userId);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

    public static boolean doesTransactionHistoryExists(long id) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "SELECT * FROM transaction_history WHERE " +
                "transaction_history.id = ?";
        Connect connect = Connect.getInstance();
        PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }


    public boolean isHasPaid() {
        return hasPaid;
    }
}
