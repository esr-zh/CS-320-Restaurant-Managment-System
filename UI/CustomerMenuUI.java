package UI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CustomerMenuUI implements ActionListener {
    public static int yCoordinate = 50;
    public static JFrame customerFrame;
    public static JPanel menuPanel, cartPanel, navBar;
    public static JLabel menuLabel, productLabel, quantityLabel, priceLabel, priceCalculatedLabel;
    public static JComboBox<String> menuDropdown;
    public static JComboBox<String> productDropdown;
    public static JComboBox<String> quantityDropdown;
    public static JTable table;
    public static JButton addButton, deleteButton, checkoutButton, menuButton, transactionButton;
    public static String[] options = {"Yes", "No"}, productTypes;

    public static DefaultTableModel tableModel;

    public static void generateCustomerUI() {
        customerFrame = new JFrame();
        setFrameProperties();

        menuPanel = new JPanel();
        cartPanel = new JPanel();
        navBar = new JPanel();

        menuPanel.setLayout(null);
        cartPanel.setLayout(null);
        navBar.setLayout(new GridLayout(1,2));

        menuButton = new JButton("Menu");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        transactionButton=new JButton("Transaction History");
        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMenu menu, TH;
                JFrame frame = new JFrame("Menu and MenuItem Example");

                TransactionHistUI transactionHistUI = new TransactionHistUI();;
                frame.add(transactionHistUI.getUIComponent());

                JMenuBar mb = new JMenuBar();
                menu = new JMenu("Menu");
                TH = new JMenu("Transaction History");

                mb.add(menu);
                mb.add(TH);
                frame.setJMenuBar(mb);

                frame.setSize(800,800);
                frame.setVisible(true);
            }
        });


        navBar.add(menuButton);
        navBar.add(transactionButton );


        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart"));


        cartPanel.setBounds(0, 370, 700, 500);
        menuPanel.setBounds(0, 20, 700, 350);
        navBar.setBounds(0,0,700,20);


        String[] menuTypes = {"Appetizer", "Main Dish", "Dessert", "Drink"};
        String[] quantity = {"1", "2", "3", "4", "5", "6", "7"};
        productTypes = new String[]{"Salad"};

        menuLabel = new JLabel("Select Menu Type:");
        productLabel = new JLabel("Select Product:");
        quantityLabel = new JLabel("Select Quantity:");
        priceLabel = new JLabel("Calculated Price:");
        priceCalculatedLabel = new JLabel("$100");

        menuDropdown = new JComboBox<>(menuTypes);
        productDropdown = new JComboBox<>(productTypes);
        quantityDropdown = new JComboBox<>(quantity);

        addComponent(menuLabel, menuDropdown);
        addComponent(productLabel, productDropdown);
        addComponent(quantityLabel, quantityDropdown);
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

        customerFrame.add(navBar);
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

    private static void addComponent(JLabel label, JComboBox<String> dropdown) {
        label.setBounds(50, yCoordinate, 150, 20);
        //dropdown = new JComboBox<>(list);
        dropdown.setBounds(400, yCoordinate, 150, 20);
        dropdown.addActionListener(e -> {
            if (Objects.equals(menuDropdown.getSelectedItem(), "Appetizer")){
                productDropdown.removeAllItems();
                productDropdown.addItem("Salad");
            }
            if(Objects.equals(menuDropdown.getSelectedItem(), "Main Dish")){
                productDropdown.removeAllItems();
                productDropdown.addItem("Cheeseburger");
            }
            if(Objects.equals(menuDropdown.getSelectedItem(), "Dessert")){
                productDropdown.removeAllItems();
                productDropdown.addItem("Cake");
            }
            if(Objects.equals(menuDropdown.getSelectedItem(), "Drink")){
                productDropdown.removeAllItems();
                productDropdown.addItem("Water");
            }
        });
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
        customerFrame.setSize(700, 750);
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