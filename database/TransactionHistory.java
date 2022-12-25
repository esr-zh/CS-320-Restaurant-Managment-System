package database;

import database.utils.Connect;
import database.utils.Template;

import java.sql.*;
import java.util.List;

public class TransactionHistory implements Template,Cloneable{
    Connection conn;
    private long id;
    private long userId;
    private long createdAt;
    private boolean hasPaid;
    private long paidAt;

    public TransactionHistory(Connection conn) {
        this.conn = conn;
    }

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

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
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

    public boolean getHasPaid(){return hasPaid;}

    public long getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(long paidAt) {
        this.paidAt = paidAt;
    }

    // list transaction history by user id
    public List<List<String>> getTransactionHistoryByUserId(int userId) throws SQLException, ClassNotFoundException {

        String SQL_QUERY = "SELECT transaction_history.id FROM transaction_history WHERE transaction_history.user_id = ?";
        return getLists(userId, SQL_QUERY);
    }

    // get add to card table
    public List<List<String>> getAddToCartTableByUserId(int userId) throws SQLException, ClassNotFoundException {

        String SQL_QUERY = "SELECT m.dish_type_id,name,OD.quantity,price from transaction_history as TH JOIN order_details OD ON OD.transaction_id = TH.id JOIN menu m ON m.id = OD.menu_id where user_id = ? AND has_paid = false;";
        return getLists(userId, SQL_QUERY);
    }

    public List<List<String>> getTransactionHistoryTableByUserId(int userId) throws SQLException, ClassNotFoundException {

        String SQL_QUERY = "SELECT TH.id, name,price,od.quantity from transaction_history as TH JOIN order_details od ON od.transaction_id = TH.id\n" +
                "                JOIN menu m ON m.id = od.menu_id where TH.user_id = ? AND has_paid = true;";
        return getLists(userId, SQL_QUERY);
    }

    // list transaction history by user id
    public boolean updatePaidStatus() throws SQLException {
        String SQL_QUERY = "UPDATE transaction_history SET has_paid = ?,paid_at = ?  WHERE transaction_history.id = ? AND transaction_history.user_id = ?";
        if (doesTransactionHistoryExists(id)){
            try (
                    PreparedStatement statement = conn.prepareStatement(SQL_QUERY,
                            Statement.RETURN_GENERATED_KEYS);
            ) {
                long currentDate = new java.util.Date().getTime();
                statement.setLong(1, 1);
                statement.setLong(2, currentDate);
                statement.setLong(3, id);
                statement.setLong(4, userId);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating transaction history failed, no rows affected.");
                }

                setHasPaid(true);
                return true;
            }
        }
        throw new SQLException("transaction id does not exist");
    }

    private List<List<String>> getLists(int userId, String SQL_QUERY) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();
        return Connect.returnArraylist(rs);
    }



    // create transaction history
    // user should not create any new history if the user didn't pay the previous one
    public TransactionHistory createTransactionHistory() throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "INSERT INTO transaction_history(user_id,created_at,has_paid,paid_at) VALUES (?, ?, ?, ?)";
        if (!doesUserPaidAll(userId)){
            try (
                    PreparedStatement statement = conn.prepareStatement(SQL_QUERY,
                            Statement.RETURN_GENERATED_KEYS);
            ) {
                long currentDate = new java.util.Date().getTime();
                statement.setLong(1, userId);
                statement.setLong(2, currentDate);
                statement.setLong(3, hasPaid ? 1 : 0);
                statement.setLong(4, paidAt);

                statement.executeUpdate();

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    System.out.println(generatedKeys);
                    if (generatedKeys.next()) {
                        this.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating transaction history, no ID obtained.");
                    }
                }

                return this;
            }
        }

        throw new SQLException("user should not create any new history if the user didn't pay the previous one");

    }

    private boolean doesUserPaidAll(long userId) throws SQLException, ClassNotFoundException {
        if (!doesUserHasTH(userId))// check if the user has any TH
            return false;

        String SQL_QUERY = "SELECT * FROM transaction_history WHERE " +
                    "transaction_history.user_id = ? AND transaction_history.has_paid = false;";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setLong(1, userId);
        ResultSet rs = statement.executeQuery();
        return rs.next();

    }


    private boolean doesUserHasTH(long userId) throws SQLException {
        String SQL_QUERY = "SELECT * FROM transaction_history WHERE " +
                "transaction_history.user_id = ?";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setLong(1, userId);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

    public boolean doesTransactionHistoryExists(long id) throws SQLException {
        System.out.println(id);
        String SQL_QUERY = "SELECT * FROM transaction_history WHERE transaction_history.id = ?";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        if (!rs.next()){
            throw new SQLException("transaction id does not exist");
        }
        return true;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }
}
