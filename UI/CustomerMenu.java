package UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        botPanel.setLayout(new BorderLayout());

        JPanel subBotPanelW = new JPanel();
        botPanel.add(subBotPanelW, BorderLayout.WEST);
        subBotPanelW.setLayout(new BorderLayout());

        JLabel cart = new JLabel("Currently in cart");
        subBotPanelW.add(cart, BorderLayout.PAGE_START);


        JPanel subBotPanelE = new JPanel();
        botPanel.add(subBotPanelE, BorderLayout.EAST);
        subBotPanelE.setLayout(new BorderLayout());

        JButton addButton = new JButton("Add to cart");
        subBotPanelE.add(addButton, BorderLayout.NORTH);


        JButton delButton = new JButton("Delete from cart");
        delButton.addActionListener(new Delete());
        subBotPanelE.add(delButton, BorderLayout.CENTER);

        JButton checkoutButton = new JButton("Checkout Button");
        checkoutButton.addActionListener(new Checkout());
        subBotPanelE.add(checkoutButton, BorderLayout.SOUTH);


        String[] menuTypes ={"Appetizer","Main Dish","Dessert","Drink"};
        JComboBox menuTypeBar = new JComboBox(menuTypes);

        String[] products =  new String[1];
        products[0]="Soup";
        JComboBox productsList = new JComboBox(products);
        menuTypeBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getMenuType = menuTypeBar.getSelectedItem().toString();
                if(menuTypeBar.getSelectedItem().toString().equals("Appetizer")){
                    productsList.removeAllItems();
                    productsList.addItem("Soup");
                }else  if (menuTypeBar.getSelectedItem().toString().equals("Main Dish")) {
                    productsList.removeAllItems();
                    productsList.addItem("Cheeseburger");
                }else if (getMenuType.equals("Dessert")) {
                    productsList.removeAllItems();
                    productsList.addItem("Cheesecake");
                }else  if (getMenuType.equals("Drink")) {
                    productsList.removeAllItems();
                    productsList.addItem("Water");
                }
            }
        });






         String[] quantities = new String[11];
         for(int i = 0; i<11;i++){
             quantities[i]= ""+i;
         }
         JComboBox quantityBar = new JComboBox(quantities);


        JLabel priceLabel = new JLabel();

         productsList.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if(productsList.getSelectedItem()!=null){
                     String getFoodPrice = productsList.getSelectedItem().toString();
                     if(getFoodPrice.equals("Soup")){
                         priceLabel.setText("$3");
                     } else if (getFoodPrice.equals("Cheeseburger")) {
                         priceLabel.setText("$4.99");
                     } else if (getFoodPrice.equals("Cheesecake")) {
                         priceLabel.setText("$5");
                     } else if (getFoodPrice.equals("Water")) {
                         priceLabel.setText("$0.5");
                     }
                 }else{
                 priceLabel.setText("0");
                 }
             }



         });


        String[][] data = new String[10][10];
        String[] columnNames = {"Type", "Name", "Price"};
        JTable itemTable = new JTable(data,columnNames);
        subBotPanelW.add(itemTable, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productsList.getSelectedItem() != null) {
                    String getTypeName = menuTypeBar.getSelectedItem().toString();
                    String getProductName = productsList.getSelectedItem().toString();
                    String getFoodPrice2 = priceLabel.getText();
                    String getQuantity = quantityBar.getSelectedItem().toString();
                    int x =15;
                    switch (getProductName) {
                        case"Soup":
                            x=0;
                            break;
                        case "Cheeseburger":
                            x=1;
                            break;
                        case "Cheesecake":
                            x=2;
                            break;
                        case "Water":
                            x=3;
                            break;
                    }
                    data[x][0] = getTypeName;
                    data[x][1] = getProductName;
                    data[x][2] = getFoodPrice2 + "*" + getQuantity;

                }
            }
        });
         

         
         midPanel.add(new MenuLabelPart("Select Menu Type:"));//first column first row
        midPanel.add(menuTypeBar);//second column first row
        midPanel.add(new MenuLabelPart("Select product:"));//first column second row
        midPanel.add(productsList);//second column second row
        midPanel.add(new MenuLabelPart("Quantity:"));
        midPanel.add(quantityBar);
        midPanel.add(new MenuLabelPart("Price of product"));
        midPanel.add((priceLabel));




        frame.setVisible(true);
    }

    public JFrame getUI(){
        return frame;
    }

    public static void main(String[] args) {
        CustomerMenu customerMenu = new CustomerMenu();
        customerMenu.getUI();
    }
public class Delete implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
public class Add implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
public class Checkout implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
JFrame frame2 = new JFrame("Checkout Page");
frame2.setVisible(true);
frame2.setSize(200,200);
JLabel checkoutLabel = new JLabel("You successfully checked-out");
JPanel checkoutPanel = new JPanel();
checkoutPanel.add(checkoutLabel);
frame2.add(checkoutPanel);
    }
}

}
