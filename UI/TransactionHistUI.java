package UI;

import javax.swing.*;

public class TransactionHistUI extends JFrame {

    JTable table;

    String[]columns = {"Transaction ID","Name","Price","Quantity"};
    String[][] cells= {
            {"12.12.2022","Pasta","500","12456"}
    };

    public TransactionHistUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table = new JTable(cells,columns);
        table.setBounds(200,200,200,200);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Transaction History Table"));
        this.add(scrollPane);
        this.setSize(800,800);
        this.setVisible(true);
    }


}
