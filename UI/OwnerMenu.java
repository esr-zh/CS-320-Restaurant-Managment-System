package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class OwnerMenu implements ActionListener {
    public static JPanel panel;
    public static JFrame frame;
    public static JTextArea descText;
    public static JLabel typeLabel, nameLabel, priceLabel, lb1, quantityLabel, descLabel, portionLabel;
    public static JTextField nameText, priceText, quantityText, portionText;
    public static JButton button;
    public static String name, price, quantity, portion, description;


    public static void generateUI(){
        panel = new JPanel();
        panel.setLayout(null);
        frame = new JFrame();
        frame.setTitle("Edit Menu");
        frame.setSize(500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        String[] type = {"Appetizer","Main Dish","Dessert","Drink"};

        lb1 = new JLabel("Edit the following item your preference: ");
        lb1.setBounds(5,10,300,20);
        panel.add(lb1);

        typeLabel = new JLabel("Item Type: ");
        typeLabel.setBounds(100, 60,140, 20);
        panel.add(typeLabel);
        JComboBox<String> l = new JComboBox<>(type);
        l.setBounds(200, 60, 140,25);
        panel.add(l);

        nameLabel = new JLabel("Item Name: ");
        nameText =  new JTextField();
        nameLabel.setBounds(100, 120,140, 20);
        panel.add(nameLabel);
        nameText.setBounds(200,120,140,25);
        nameText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(nameText);

        priceLabel = new JLabel("Item Price: ");
        priceText = new JTextField();
        priceLabel.setBounds(100, 180,140, 20);
        panel.add(priceLabel);
        priceText.setBounds(200,180,140,25);
        priceText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(priceText);

        quantityLabel = new JLabel("Item Quantity: ");
        quantityText = new JTextField();
        quantityLabel.setBounds(100, 240,140,20);
        panel.add(quantityLabel);
        quantityText.setBounds(200, 240,140,25);
        quantityText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(quantityText);

        portionLabel = new JLabel("Item Portion:");
        portionText = new JTextField();
        portionLabel.setBounds(100, 300,140,20);
        panel.add(portionLabel);
        portionText.setBounds(200, 300,140,25);
        portionText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(portionText);

        descLabel = new JLabel("Item Description:");
        descText = new JTextArea();
        descLabel.setBounds(100, 360,140,20);
        panel.add(descLabel);
        descText.setBounds(200, 360,140,80);
        descText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(descText);


        button = new JButton("Confirm");
        button.setBounds(180, 480,120,30);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setOpaque(true);
        button.addActionListener(new OwnerMenu());
        panel.add(button);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            JOptionPane.showMessageDialog(null, "Changes to item have been saved!");
            name = nameText.getText();
            price = priceText.getText();
            quantity = quantityText.getText();
            portion = portionText.getText();
            description = descText.getText();
            System.out.println(name + "\n" + price + "\n" + quantity +
                    "\n" + portion + "\n" + description);
        }
    }
}
