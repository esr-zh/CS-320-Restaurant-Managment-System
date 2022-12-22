package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class OwnerMenu implements ActionListener {
    public static int yCoordinate  = 60;
    public static JPanel panel;
    public static JFrame frame;
    public static JTextArea descText;
    public static JLabel typeLabel, nameLabel, priceLabel, lb1, quantityLabel, descLabel, portionLabel;
    public static JTextField productName, productPrice, productQuantity, portionText;
    public static JButton button;
    public static JComboBox<String> productType;
    public static String typep, name, price, quantity, portion, description;
    public static String[] type = {"Appetizer","Main Dish","Dessert","Drink"};


    public static void generateUI(){
        panel = new JPanel();
        panel.setLayout(null);
        frame = new JFrame();
        frame.setTitle("Edit Menu");
        frame.setSize(500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        button = new JButton("Confirm");
        btnProperties(button);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            JOptionPane.showMessageDialog(null, "Changes to item have been saved!");
            typep  = (String) productType.getSelectedItem();
            name = productName.getText();
            price = productPrice.getText();
            quantity = productQuantity.getText();
            portion = portionText.getText();
            description = descText.getText();
            System.out.println(typep + "\n" + name + "\n" + price + "\n" + quantity +
                    "\n" + portion + "\n" + description);
        }
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
        b.addActionListener(new OwnerMenu());
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
