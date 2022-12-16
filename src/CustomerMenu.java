
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class CustomerMenu {
    public static customerMenu() {
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        JPanel topPanel = new JPanel();
        frame.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new GridLayout(1,3));

        JPanel midPanel = new JPanel();
        frame.add(midPanel, BorderLayout.CENTER);
        midPanel.setLayout(new GridLayout(4,2));

        JPanel botPanel = new JPanel();
        frame.add(botPanel, BorderLayout.SOUTH);
        botPanel.setLayout(new GridLayout(4,4));


        String[] menuTypes ={"Appetizer","Main Dish","Dessert","Drink"};
        JComboBox menuTypeBar = new JComboBox(menuTypes);

         String[] products ={"Salad", "Soup", "Burger","Pizza"};
         JComboBox productsList = new JComboBox(products);

         topPanel.add(new MenuButtonPart("Menu"));//those 3 are the buttons that appear on the top
        topPanel.add(new MenuButtonPart("Checkout"));
        topPanel.add(new MenuButtonPart("Transaction History"));

        midPanel.add(new MenuLabelPart("Select Menu Type:"));//first column first row
        midPanel.add(menuTypeBar);//second column first row
        midPanel.add(new MenuLabelPart("Select product:"));//first column second row
        midPanel.add(productsList);//second column second row
        midPanel.add(new MenuLabelPart("Quantity:"));
        midPanel.add(new MenuLabelPart("quantity of the product will be shown here"));
        midPanel.add(new MenuLabelPart("Price of product"));
        midPanel.add(new MenuLabelPart("$price of the product will be shown here"));

        botPanel.add(new MenuLabelPart("Currently in cart"));
        botPanel.add(new MenuLabelPart(""));//those are empty spaces after currently in cart
        botPanel.add(new MenuLabelPart(""));
        botPanel.add(new MenuLabelPart(""));
        botPanel.add(new MenuLabelPart("__________"));
        botPanel.add(new MenuLabelPart("__________"));
        botPanel.add(new MenuLabelPart("__________"));
        botPanel.add(new MenuButtonPart("Del Button"));
        botPanel.add(new MenuLabelPart("__________"));
        botPanel.add(new MenuLabelPart("__________"));
        botPanel.add(new MenuLabelPart("__________"));
        botPanel.add(new MenuButtonPart("Del Button"));
        botPanel.add(new MenuLabelPart(""));
        botPanel.add(new MenuLabelPart(""));
        botPanel.add(new MenuLabelPart(""));
        botPanel.add(new MenuButtonPart("Checkout Button"));






        frame.setVisible(true);
    }
}
