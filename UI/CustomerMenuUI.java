package UI;
import database.DishType;
import database.Menu;
import database.utils.Connect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class CustomerMenuUI implements ActionListener {
    public static Connect connect = new Connect();
    public static Menu menu = new Menu(connect.connection);
    public static int yCoordinate = 50;
    public static JPanel customerMainPanel;
    public static JPanel menuPanel, cartPanel;
    public static JLabel menuLabel, productLabel, quantityLabel, priceLabel, priceCalculatedLabel;
    public static JComboBox<String> menuDropdown;
    public static JComboBox<String> productDropdown;
    public static JComboBox<String> quantityDropdown;
    public static JTable table;
    public static JButton addButton, deleteButton, checkoutButton, menuButton, transactionButton;
    public static String[] options = {"Yes", "No"}, productTypes;

    public static DefaultTableModel tableModel;

    public static JPanel generateCustomerUI() {
        customerMainPanel = new JPanel();
        setFrameProperties();

        menuPanel = new JPanel();
        cartPanel = new JPanel();

        menuPanel.setLayout(null);
        cartPanel.setLayout(null);
        transactionButton=new JButton("Transaction History");
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart"));
        cartPanel.setBounds(0, 370, 700, 500);
        menuPanel.setBounds(0, 20, 700, 350);

        String[] menuTypes = {"Appetizer", "Main Dish", "Dessert", "Drinks"};
        productTypes = new String[]{""};

        menuLabel = new JLabel("Select Menu Type:");
        menuLabelProperties(menuLabel);
        productLabel = new JLabel("Select Product:");
        menuLabelProperties(productLabel);
        quantityLabel = new JLabel("Select Quantity:");
        menuLabelProperties(quantityLabel);
        priceLabel = new JLabel("Calculated Price:");
        menuLabelProperties(priceLabel);
        priceCalculatedLabel = new JLabel("0 TL");
        menuPanel.add(priceCalculatedLabel);
        priceCalculatedLabel.setBounds(400, yCoordinate - 60, 150, 20);
        yCoordinate = 60;

        menuDropdown = new JComboBox<>(menuTypes);
        menuPanel.add(menuDropdown);
        productDropdown = new JComboBox<>();
        menuPanel.add(productDropdown);
        quantityDropdown = new JComboBox<>();
        menuPanel.add(quantityDropdown);
        updateProductDropdown(menuDropdown, productDropdown);
        updateQuantityDropdown(productDropdown, quantityDropdown);
        updatePriceCalculatedLabel(quantityDropdown,priceCalculatedLabel);
        addTable();

        checkoutButton = new JButton("Checkout");
        btnProperties(checkoutButton);
        checkoutButton.setBounds(260, 280, 150, 25);
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setBackground(Color.BLACK);
        cartPanel.add(checkoutButton);

        addButton = new JButton("Add to Cart");
        btnProperties(addButton);
        addButton.setBounds(410, 300, 120, 25);
        menuPanel.add(addButton);

        deleteButton = new JButton("Delete");
        btnProperties(deleteButton);
        deleteButton.setBounds(550, 240, 100, 25);
        btnProperties(deleteButton);
        cartPanel.add(deleteButton);

        customerMainPanel.add(menuPanel);
        customerMainPanel.add(cartPanel);
        customerMainPanel.setVisible(true);

        return customerMainPanel;
    }

    private static void menuLabelProperties(JLabel label) {
        menuPanel.add(label);
        label.setBounds(50, yCoordinate, 150, 20);
        yCoordinate+= 60;
    }


    private static void addTable() {
        String[][] data = {
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
        };
        // Column Names
        String[] columnNames = {"Menu", "Product", "Quantity", "Price"};
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //prevent a user from selecting multiple rows
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10, 20, 660, 200);
        cartPanel.add(sp);
    }

    private static void updateProductDropdown(JComboBox<String> watchingDropdown, JComboBox<String> changeDropdown) {
        DishType dishType = new DishType();
        watchingDropdown.setBounds(400, yCoordinate, 150, 20);
        watchingDropdown.addActionListener(e -> {
            changeDropdown.removeAllItems();
            if (Objects.equals(menuDropdown.getSelectedItem(), "Appetizer")){
                int dishTypeNum = dishType.getDishType("appetizer");
                addItemsList(changeDropdown, dishTypeNum);
            }
            if(Objects.equals(watchingDropdown.getSelectedItem(), "Main Dish")){
                int dishTypeNum = dishType.getDishType("main dish");
                addItemsList(changeDropdown, dishTypeNum);
            }
            if(Objects.equals(menuDropdown.getSelectedItem(), "Dessert")){
                int dishTypeNum = dishType.getDishType("dessert");
                addItemsList(changeDropdown, dishTypeNum);
            }
            if(Objects.equals(watchingDropdown.getSelectedItem(), "Drinks")){
                int dishTypeNum = dishType.getDishType("drinks");
                addItemsList(changeDropdown, dishTypeNum);
            }
        });
        yCoordinate += 60;
    }
    public static Menu selectedItem;
    private static void updateQuantityDropdown(JComboBox<String> watchingDropdown, JComboBox<String> changeDropdown) {
        watchingDropdown.setBounds(400, yCoordinate, 150, 20);
        yCoordinate += 60;
        watchingDropdown.addActionListener(e -> {
            changeDropdown.removeAllItems();
            System.out.println(productDropdown.getSelectedItem());
            try {
                selectedItem = menu.getMenuByName((String) productDropdown.getSelectedItem());
                for (int i = 0; i < selectedItem.getQuantity(); i++) {
                    changeDropdown.addItem(String.valueOf(i+1));
                }
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private static void updatePriceCalculatedLabel(JComboBox<String> quantityDropdown, JLabel priceCalculatedLabel) {
        quantityDropdown.setBounds(400, yCoordinate, 150, 20);
//        Object q =  quantityDropdown.getSelectedItem();
//        System.out.println(selectedItem.getPrice());
//        quantityDropdown.addActionListener(e -> {
//            priceCalculatedLabel.setText("0 TL");
//            System.out.println(q);
//            System.out.println(selectedItem.getPrice());

//                priceCalculatedLabel.
//                        setText(
//                                String.format("%f TL",
//                                        selectedItem.getPrice() * q)
//                        );
//        });
    }

    private static void addItemsList(JComboBox<String> menuDropdown, int dishTypeNum) {
        List<List<String>> res = menu.getMenuItemsByDishType(dishTypeNum);
        for(List<String> row : res){
            menuDropdown.addItem(row.get(1));
        }
        System.out.println(res);
    }

    private static void addLabelToPanel(JLabel label, JLabel priceCalculatedLabel) {
        label.setBounds(50, yCoordinate, 150, 20);
        priceCalculatedLabel.setBounds(400, yCoordinate, 150, 20);
        menuPanel.add(label);
        menuPanel.add(priceCalculatedLabel);
        yCoordinate += 60;
    }

    private static void setFrameProperties() {
        customerMainPanel.setLayout(null);
    }

    private static void btnProperties(JButton button) {
        button.setBorderPainted(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener((ActionListener) new CustomerMenuUI());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton && table.getSelectedRow() != -1) {
            tableModel.removeRow(table.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Selected product removed from cart");
        }
        if (e.getSource() == addButton){
            JOptionPane.showMessageDialog(null, "Product added to cart!");
        }
        if (e.getSource() == checkoutButton){
            JOptionPane.showOptionDialog(null,
                    "Do you want a receipt?",
                    "Checkout Success",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        }
    }
}