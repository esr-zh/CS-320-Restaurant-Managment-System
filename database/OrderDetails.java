package database;

import database.utils.*;
import java.sql.*;
import java.util.List;

public class OrderDetails implements Template,Cloneable {
    private Connection conn;
    private long id;
    private long transactionId;
    private long quantity;
    private long menuId;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public OrderDetails(Connection conn) {
        this.conn = conn;
    }

    public OrderDetails(long transactionId, long quantity, long menuId) {
        this.transactionId = transactionId;
        this.quantity = quantity;
        this.menuId = menuId;
    }

    // create order details
    public boolean addOrderDetails() throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "INSERT INTO order_details(transaction_id,quantity,menu_id) VALUES (?, ?, ?)";
        new TransactionHistory(conn).doesTransactionHistoryExists(transactionId);
        new Menu(conn).getMenuByIdBool(menuId);
        try (
                PreparedStatement statement = conn.prepareStatement(SQL_QUERY,
                        Statement.RETURN_GENERATED_KEYS);
            ) {
                statement.setLong(1, transactionId);
                statement.setLong(2, quantity);
                statement.setLong(3, menuId);
                return true;
            }

    }

    public List<List<String>> getOrderDetailsByTransactionId(int id) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "select transaction_id,name,order_details.quantity,price from order_details join menu m on m.id = order_details.menu_id WHERE transaction_id = ?";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        return Connect.returnArraylist(rs);
    }

    public boolean deleteOrderDetailsById() throws SQLException {
        findOrderDetailsById(id);
        String SQL_QUERY = "DELETE FROM order_details WHERE order_details.id = ?";
        try (
                PreparedStatement statement = conn.prepareStatement(SQL_QUERY,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("deleting menu failed, no rows affected.");
            }

        }
        return true;
    }

    public Boolean findOrderDetailsById(long id) throws SQLException {
        String SQL_QUERY = "SELECT * FROM order_details WHERE order_details.id = ?;";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        if (!rs.next())
            throw new SQLException("order details id not found");
        return true;
    }

    public int getTotalPriceByTransactionId() throws SQLException {
        String SQL_QUERY = "select sum(order_details.quantity * price) as total from order_details join menu m on m.id = order_details.menu_id where transaction_id = ? group by price, name, order_details.quantity, menu_id";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setLong(1, transactionId);
        ResultSet rs = statement.executeQuery();
        int res = 0;
        int i = 1;
        while (rs.next()) {
            res += rs.getInt(i++);
        }
        return res;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
