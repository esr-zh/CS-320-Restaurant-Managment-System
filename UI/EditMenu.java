package UI;

import database.DishType;
import database.Menu;
import database.utils.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class EditMenu {
    public static Connect connect = new Connect();
    public static database.Menu menu = new Menu(connect.connection);
    public static int yCoordinate  = 60;
    public static JPanel panel;
    public static JFrame frame;
    public static JTextArea descText;
    public static long itemId;
    public static JLabel typeLabel, nameLabel, priceLabel, lb1, quantityLabel, descLabel, portionLabel;
    public static JTextField productName, productPrice, productQuantity, portionText;
    public static JButton confirmBtn;
    public static JComboBox<String> productType;
    public static String typep, name, price, quantity, portion, description;
    public static String[] type = {"appetizer","main dish","dessert","drinks"};


    public static void generateUI(){
        panel = new JPanel();
        panel.setLayout(null);
        frame = new JFrame();
        frame.setTitle("Edit Menu");
        frame.setSize(500,600);
        yCoordinate = 60;
        frame.add(panel);


        lb1 = new JLabel("Edit the following item to your preference: ");
        lb1.setBounds(5,10,300,20);
        panel.add(lb1);

        //Creating a drop-down menu
        typeLabel = new JLabel("Type: ");
        typeLabel.setBounds(100, yCoordinate,140, 20);
        panel.add(typeLabel);
        productType = new JComboBox<>(type);
        productType.setBounds(200, yCoordinate, 140,25);
        panel.add(productType);
        yCoordinate+=60;

        nameLabel = new JLabel("Name: ");
        productName =  new JTextField();
        addToPanel(nameLabel, productName);

        priceLabel = new JLabel("Price: ");
        productPrice = new JTextField();
        addToPanel(priceLabel, productPrice);

        quantityLabel = new JLabel("Quantity: ");
        productQuantity = new JTextField();
        addToPanel(quantityLabel, productQuantity);

        portionLabel = new JLabel("Portion:");
        portionText = new JTextField();
        addToPanel(portionLabel,portionText);

        //Creating a Text Area big enough for description
        descLabel = new JLabel("Description:");
        descText = new JTextArea();
        addTextArea(descLabel, descText);

        confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DishType dishType = new DishType();
                menu.setId(itemId);
                menu.setQuantity(Long.parseLong(productQuantity.getText()));
                menu.setName(productName.getText());
                menu.setPrice(Double.parseDouble(productPrice.getText()));
                menu.setDescription(descText.getText());
                menu.setServingAmount(Long.parseLong(portionText.getText()));
                menu.setDishTypeId(dishType.getSalaryType((String) productType.getSelectedItem()));
                try {
                    if (menu.updateMenu())
                        JOptionPane.showMessageDialog(null, "Changes to item have been saved!");
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnProperties(confirmBtn);

        frame.setVisible(true);
    }


    public static void addToPanel(JLabel label, JTextField text){
        label.setBounds(100, yCoordinate,140, 20);
        panel.add(label);
        text.setBounds(200, yCoordinate,140,25);
        panel.add(text);
        yCoordinate+=60;
    }

    public static void btnProperties(JButton b){
        b.setBounds(180, yCoordinate+60,120,30);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setOpaque(true);
        panel.add(b);
    }

    public static void addTextArea(JLabel label, JTextArea Atext){
        label.setBounds(100, yCoordinate,140,20);
        panel.add(descLabel);
        Atext.setBounds(200, yCoordinate,140,80);
        Atext.setLineWrap(true);
        Atext.setWrapStyleWord(true);
        panel.add(Atext);
        yCoordinate+=60;
    }

}
