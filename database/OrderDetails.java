package database;

import database.utils.Helper;
import database.utils.Template;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDetails implements Template,Cloneable {
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

    private OrderDetails(Builder builder){
        this.id = builder.id;
        this.transactionId = builder.transactionId;
        this.quantity = builder.quantity;
        this.menuId = builder.menuId;
    }

    public OrderDetails(long transactionId, long quantity, long menuId) {
        this.transactionId = transactionId;
        this.quantity = quantity;
        this.menuId = menuId;
    }

    // create order details
    public static OrderDetails createOrderDetails(OrderDetails OD) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "INSERT INTO order_details(transaction_id,quantity,menu_id) VALUES (?, ?, ?)";
        Connect connect = Connect.getInstance();
        if (TransactionHistory.doesTransactionHistoryExists(OD.transactionId)){
            try (
                    PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY,
                            Statement.RETURN_GENERATED_KEYS);
            ) {
                statement.setLong(1, OD.transactionId);
                statement.setLong(2, OD.quantity);
                statement.setLong(3, OD.menuId);

                return Helper.executeAndGetId(OD,statement);
            }

        }

        throw new SQLException("transaction id does not exists");

    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Builder {
        private final long id;
        private long transactionId;
        private long quantity;
        private long menuId;

        public Builder(long id) {
            this.id = id;
        }

        public Builder setTransactionId(long id){
            this.transactionId = id;
            return this;
        }

        public Builder setQuantity(long quantity){
            this.quantity = quantity;
            return this;
        }

        public Builder setMenuId(long menuId){
            this.menuId = menuId;
            return this;
        }

        public OrderDetails build() {
            return new OrderDetails(this);
        }
    }

}
