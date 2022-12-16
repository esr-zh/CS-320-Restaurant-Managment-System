package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OwnerMenu {
    public static JPanel panel;
    public static JFrame frame;
    public static JLabel typeLabel, nameLabel, priceLabel, lb1;
    public static JTextField nameText, priceText;



    public static void main(String[] args){
        panel = new JPanel();
        panel.setLayout(null);
        frame = new JFrame();
        frame.setTitle("Edit Menu");
        frame.setSize(500,500);
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
        panel.add(nameText);

        priceLabel = new JLabel("Item Price: ");
        priceText = new JTextField();
        priceLabel.setBounds(100, 180,140, 20);
        panel.add(priceLabel);
        priceText.setBounds(200,180,140,25);
        panel.add(priceText);

        frame.setVisible(true);
    }


}
