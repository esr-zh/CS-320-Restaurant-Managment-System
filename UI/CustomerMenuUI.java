package UI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CustomerMenuUI implements ActionListener {
    public static int yCoordinate = 50;
    public static JFrame customerFrame;
    public static JPanel menuPanel, cartPanel;
    public static JLabel menuLabel, productLabel, quantityLabel, priceLabel, priceCalculatedLabel;
    public static JComboBox menuDropdown, productDropdown, quantityDropdown;
    public static JTable table;
    public static JButton addButton, deleteButton, checkoutButton;
    public static String[] options = {"Yes", "No"};

    public static DefaultTableModel tableModel;

    public static void generateCustomerUI() {
//        JPanel panel = new JPanel();
//        panel.setLayout(null);

        customerFrame = new JFrame();
        setFrameProperties();

        menuPanel = new JPanel();
        cartPanel = new JPanel();

        menuPanel.setLayout(null);
        cartPanel.setLayout(null);

        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart"));

        cartPanel.setBounds(0, 350, 700, 500);
        menuPanel.setBounds(0, 0, 700, 350);

        menuLabel = new JLabel("Select Menu Type:");
        String[] menuTypes = {"Appetizer", "Main Dish", "Dessert", "Drink"};
        String[] productTypes = {"Cake"};
        String[] quantity = {"1", "2", "3", "4", "5", "6", "7"};
        productLabel = new JLabel("Select Product:");
        quantityLabel = new JLabel("Select Quantity:");
        priceLabel = new JLabel("Calculated Price:");
        priceCalculatedLabel = new JLabel("$100");

        addComponent(menuLabel, menuTypes);
        addComponent(productLabel, productTypes);
        addComponent(quantityLabel, quantity);
        addLabelToPanel(priceLabel, priceCalculatedLabel);
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

        customerFrame.add(menuPanel);
        customerFrame.add(cartPanel);
        customerFrame.setVisible(true);
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

    private static void addComponent(JLabel label, String[] list) {
        label.setBounds(50, yCoordinate, 150, 20);
        JComboBox dropdown = new JComboBox<>(list);
        dropdown.setBounds(400, yCoordinate, 150, 20);
        menuPanel.add(label);
        menuPanel.add(dropdown);
        yCoordinate += 60;
    }

    private static void addLabelToPanel(JLabel label, JLabel priceCalculatedLabel) {
        label.setBounds(50, yCoordinate, 150, 20);
        priceCalculatedLabel.setBounds(400, yCoordinate, 150, 20);
        menuPanel.add(label);
        menuPanel.add(priceCalculatedLabel);
        yCoordinate += 60;
    }

    private static void setFrameProperties() {
        customerFrame.setLayout(null);
        customerFrame.setTitle("Welcome");
        customerFrame.setResizable(false);
        customerFrame.setSize(700, 700);
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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