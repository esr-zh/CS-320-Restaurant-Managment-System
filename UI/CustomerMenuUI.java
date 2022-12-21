package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class CustomerMenuUI {
    public static int yCoordinate=100;
    public static JFrame customerFrame;
    public static JPanel menuPanel, cartPanel;
    public static JLabel menuLabel, productLabel, quantityLabel, priceLabel, priceCalculatedLabel;
    public static JComboBox menuDropdown, productDropdown, quantityDropdown;
    public static JButton addButton, deleteButton, checkoutButton;

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

        menuLabel = new JLabel("Select Menu Type:");
        String[] menuTypes ={"Appetizer", "Main Dish", "Dessert", "Drink"};
        String[] productTypes= {"Cake"};
        String[] quantity = {"1", "2", "3", "4", "5", "6", "7"};
        productLabel = new JLabel("Select Product:");
        quantityLabel = new JLabel("Select Quantity:");
        priceLabel = new JLabel("Calculated Price:");
        priceCalculatedLabel = new JLabel("$100");

        addComponent(menuLabel, menuDropdown, menuTypes);
        addComponent(productLabel, productDropdown, productTypes);
        addComponent(quantityLabel, quantityDropdown, quantity);
        addLabelToPanel(priceLabel, priceCalculatedLabel);


        customerFrame.add(menuPanel);
        customerFrame.add(cartPanel);
        //customerFrame.pack();
        customerFrame.setVisible(true);
    }

    private static void addComponent(JLabel label, JComboBox dropdown, String[] list){
        label.setBounds(100, yCoordinate, 150, 20);
        dropdown = new JComboBox<>(list);
        dropdown.setBounds(400, yCoordinate, 150, 20);
        customerFrame.add(label);
        customerFrame.add(dropdown);
        yCoordinate+=60;
    }

    private static void addLabelToPanel(JLabel label, JLabel priceCalculatedLabel){
        label.setBounds(100, yCoordinate, 150, 20);
        priceCalculatedLabel.setBounds(400, yCoordinate, 150, 20);
        customerFrame.add(label);
        customerFrame.add(priceCalculatedLabel);
        yCoordinate+=60;

    }

    private static void setFrameProperties(){
        customerFrame.setLayout(null);
        customerFrame.setTitle("Welcome");
        customerFrame.setSize(700, 700);
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void btnProperties(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener((ActionListener) new CustomerMenuUI());
    }

}
