package database;

import database.utils.Connect;

import java.sql.*;
import java.util.List;

public class Menu {
    private Connection conn;
    private long id;
    private String name;
    private String description;
    private long servingAmount;
    private double price;
    private long dishTypeId;

    private long quantity;

    public Menu(Connection conn) {
        this.conn = conn;
    }

    public Menu(long id, String name, String description, long servingAmount, double price, long dishTypeId,long quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.servingAmount = servingAmount;
        this.price = price;
        this.dishTypeId = dishTypeId;
        this.quantity = quantity;
    }

    public Menu(String name, String description, long servingAmount, double price, long dishTypeId) {
        this.name = name;
        this.description = description;
        this.servingAmount = servingAmount;
        this.price = price;
        this.dishTypeId = dishTypeId;
    }

    public Menu() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getServingAmount() {
        return servingAmount;
    }

    public void setServingAmount(long servingAmount) {
        this.servingAmount = servingAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDishTypeId() {
        return dishTypeId;
    }

    public void setDishTypeId(long dishTypeId) {
        this.dishTypeId = dishTypeId;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    // list all menus
    public List<List<String>> listAllMenu() throws SQLException {
        String SQL_QUERY = "select menu.id,name,description,price,dish_type_name from menu join dish_type dt on dt.id = menu.dish_type_id;";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        ResultSet rs = statement.executeQuery();
        return Connect.returnArraylist(rs);
    }
    // get menu by name
    public Menu getMenuByName(String name) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "SELECT * FROM menu WHERE menu.name = ?;";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setString(1, name);
        return getAttribute(statement);

    }

    public boolean doesMenuExist(String name) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "SELECT * FROM menu WHERE menu.name = ?;";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }
    // get menu by id
    public Menu getMenuById(long id) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "SELECT * FROM menu WHERE menu.id = ?;";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setString(1, String.valueOf(id));
        return getAttribute(statement);
    }

    public boolean getMenuByIdBool(long id) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "SELECT * FROM menu WHERE menu.id = ?;";
        PreparedStatement statement = conn.prepareStatement(SQL_QUERY);
        statement.setString(1, String.valueOf(id));
        ResultSet rs = statement.executeQuery();
        if (!rs.next())
            throw new SQLException("menu id not found");
        return true;
    }

    private static Menu getAttribute(PreparedStatement statement) throws SQLException {
        ResultSet rs = statement.executeQuery();
        if (!rs.next()){
            throw new SQLException("menu item not found");
        }

        return new Menu(
                Integer.parseInt(rs.getString(1)),
                rs.getString(2),
                rs.getString(3),
                Integer.parseInt(rs.getString(4)),
                Double.parseDouble(rs.getString(5)),
                Integer.parseInt(rs.getString(6)),
                Integer.parseInt(rs.getString(7))
        );
    }
    // create menu
    // name is a unique attribute
    public Menu createMenu() throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "INSERT INTO menu(name,description,serving_amount,price,dish_type_id,quantity) VALUES (?, ?, ?, ?, ?, ?)";
        if (!doesMenuExist(name)){
            try (
                    PreparedStatement statement = conn.prepareStatement(SQL_QUERY,
                            Statement.RETURN_GENERATED_KEYS);
            ) {

                int affectedRows = execute(statement);

                if (affectedRows == 0) {
                    throw new SQLException("Creating menu failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating menu failed, no ID obtained.");
                    }
                }
            }
            return this;
        }

        throw new SQLException("menu item already exists");
    }
    // delete menu
    // it will return true if everything went successfully
    public boolean deleteMenu() throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "DELETE FROM menu WHERE menu.id = ?";
        if (getMenuByIdBool(id)){
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

        throw new SQLException("menu item doesn't exists");
    }
    // update menu
    // Menu object should pass all attribute
    public boolean updateMenu() throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "UPDATE menu SET name = ?, description = ?,serving_amount = ?," +
                "price = ?,dish_type_id = ?,quantity = ? WHERE menu.id = ?";
        Menu current = getMenuById(id);
        try (PreparedStatement statement = conn.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS);) {
                statement.setLong(7, id);

            if (name == null)
                    setName(current.getName());
                if (description == null)
                    setDescription(current.getDescription());
                if (servingAmount == 0)
                    setServingAmount(current.getServingAmount());
                if (price == 0)
                    setPrice(current.getPrice());
                if (dishTypeId == 0)
                    setDishTypeId(current.getDishTypeId());
                if (quantity == 0)
                    setDishTypeId(current.getQuantity());

            System.out.println(this);
                int affectedRows = execute(statement);
                if (affectedRows == 0) {
                    throw new SQLException("deleting menu failed, no rows affected.");
                }
                return true;
            }
    }
    public List<List<String>> getMenuItemsByDishType(int dishTypeId) {
        String SQL_QUERY = "SELECT * FROM menu WHERE menu.dish_type_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(SQL_QUERY, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, dishTypeId);
            ResultSet rs = statement.executeQuery();
            return Connect.returnArraylist(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public long getQuantity() {
        return quantity;
    }

    private int execute(PreparedStatement statement) throws SQLException {
        statement.setString(1, name);
        statement.setString(2, description);
        statement.setLong(3, servingAmount);
        statement.setDouble(4, price);
        statement.setLong(5, dishTypeId);
        statement.setLong(6, quantity);

        return statement.executeUpdate();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "conn=" + conn +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", servingAmount=" + servingAmount +
                ", price=" + price +
                ", dishTypeId=" + dishTypeId +
                ", quantity=" + quantity +
                '}';
    }
}
