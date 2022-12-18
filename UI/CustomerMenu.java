package UI;
import java.awt.*;
import javax.swing.*;

public class CustomerMenu {

    public static JFrame frame = new JFrame("Menu");

    public  CustomerMenu() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        JPanel topPanel = new JPanel();
        frame.add(topPanel, BorderLayout.NORTH);


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

         String[] quantities = new String[11];
         for(int i = 0; i<11;i++){
             quantities[i]= ""+i;
         }
         JComboBox quantityBar = new JComboBox(quantities);

        String[][] data = {
                {"Drink", "Water", "$0.5"},
                {"Dessert", "Cheesecake", "$5"},
                {"Main", "Cheeseburger", "4.99"},
        };
        String[] columnNames = {"Type", "Name", "Price"};
        JTable itemTable = new JTable(data,columnNames);

         midPanel.add(new MenuLabelPart("Select Menu Type:"));//first column first row
        midPanel.add(menuTypeBar);//second column first row
        midPanel.add(new MenuLabelPart("Select product:"));//first column second row
        midPanel.add(productsList);//second column second row
        midPanel.add(new MenuLabelPart("Quantity:"));
        midPanel.add(quantityBar);
        midPanel.add(new MenuLabelPart("Price of product"));
        midPanel.add(new MenuLabelPart("$price of the product will be shown here"));

        botPanel.add(new MenuLabelPart("Currently in cart"));
        botPanel.add(new MenuLabelPart(""));//those are empty spaces after currently in cart
        botPanel.add(new MenuLabelPart(""));
        botPanel.add(new MenuLabelPart(""));
        botPanel.add(new MenuLabelPart(""));//--- starts from here
        botPanel.add(itemTable);
        botPanel.add(new MenuLabelPart(""));//---
        botPanel.add(new MenuButtonPart("Add to cart"));
        botPanel.add(new MenuLabelPart(""));//--
        botPanel.add(new MenuLabelPart(""));//--
        botPanel.add(new MenuLabelPart(""));//--
        botPanel.add(new MenuButtonPart("Delete from cart"));
        botPanel.add(new MenuLabelPart(""));
        botPanel.add(new MenuLabelPart(""));
        botPanel.add(new MenuLabelPart(""));
        botPanel.add(new MenuButtonPart("Checkout Button"));


        frame.setVisible(true);
    }

    public JFrame getUI(){
        return frame;
    }

    public static void main(String[] args) {
        CustomerMenu customerMenu = new CustomerMenu();
        customerMenu.getUI();
    }
}
