package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Menu {
    private long id;
    private String name;
    private String description;
    private long servingAmount;
    private double price;
    private long dishTypeId;

    public Menu(long id, String name, String description, long servingAmount, double price, long dishTypeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.servingAmount = servingAmount;
        this.price = price;
        this.dishTypeId = dishTypeId;
    }

    public Menu(String name, String description, long servingAmount, double price, long dishTypeId) {
        this.id = id;
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

    // list all menus
    public static List<List<String>> listAllMenu() throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "SELECT * FROM menu;";
        Connect connect = Connect.getInstance();
        PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY);
        ResultSet rs = statement.executeQuery();
        return Connect.returnArraylist(rs);
    }
    // get menu by name
    public static Menu getMenuByName(String name) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "SELECT * FROM menu WHERE menu.name = ?;";
        Connect connect = Connect.getInstance();
        PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY);
        statement.setString(1, name);
        return getAttribute(statement);

    }

    public static boolean doesMenuExist(String name) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "SELECT * FROM menu WHERE menu.name = ?;";
        Connect connect = Connect.getInstance();
        PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        return rs.next();

    }
    // get menu by id
    public static Menu getMenuById(long id) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "SELECT * FROM menu WHERE menu.id = ?;";
        Connect connect = Connect.getInstance();
        PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY);
        statement.setString(1, String.valueOf(id));
        return getAttribute(statement);
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
                Integer.parseInt(rs.getString(6))
        );
    }
    // create menu
    // name is a unique attribute
    public static Menu createMenu(Menu menu) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "INSERT INTO menu(name,description,serving_amount,price,dish_type_id) VALUES (?, ?, ?, ?, ?)";
        Connect connect = Connect.getInstance();
        if (!doesMenuExist(menu.name)){
            try (
                    PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY,
                            Statement.RETURN_GENERATED_KEYS);
            ) {

                int affectedRows = execute(menu, statement);

                if (affectedRows == 0) {
                    throw new SQLException("Creating menu failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        menu.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating menu failed, no ID obtained.");
                    }
                }
            }
            return menu;
        }

        throw new SQLException("menu item already exists");
    }
    // delete menu
    // it will return true if everything went successfully
    public static boolean deleteMenu(Menu menu) throws SQLException, ClassNotFoundException {
        String SQL_QUERY = "DELETE FROM menu WHERE menu.id = ? OR menu.name = ?";
        Connect connect = Connect.getInstance();
        if (doesMenuExist(menu.name)){
            try (
                    PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY,
                            Statement.RETURN_GENERATED_KEYS);
            ) {
                statement.setLong(1, menu.id);
                statement.setString(2, menu.name);

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
    public static Menu updateMenu(Menu menu) throws SQLException, ClassNotFoundException {
//        name,description,serving_amount,price,dish_type_id
        String SQL_QUERY = "UPDATE menu SET name = ?, description = ?,serving_amount = ?," +
                "price = ?,dish_type_id = ? WHERE menu.id = ? OR menu.name = ?";
        Connect connect = Connect.getInstance();
        if (doesMenuExist(menu.name)){
            try (
                    PreparedStatement statement = connect.connection.prepareStatement(SQL_QUERY,
                            Statement.RETURN_GENERATED_KEYS);
            ) {
                statement.setLong(6, menu.id);
                statement.setString(7, menu.name);
                int affectedRows = execute(menu, statement);
                if (affectedRows == 0) {
                    throw new SQLException("deleting menu failed, no rows affected.");
                }
                return menu;
            }
        }
        throw new SQLException("menu item doesn't exists");
    }

    private static int execute(Menu menu, PreparedStatement statement) throws SQLException {
        statement.setString(1, menu.name);
        statement.setString(2, menu.description);
        statement.setLong(3, menu.servingAmount);
        statement.setDouble(4, menu.price);
        statement.setLong(5, menu.dishTypeId);

        return statement.executeUpdate();
    }
}
