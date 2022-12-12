package UI;

import javax.swing.*;
import javax.swing.table.TableRowSorter;

public class TransactionHistUI extends JFrame {

    JTable t;

    String[]columns= {"Date","Item","Total","Transaction ID"};
    String[][] cells= {{"12.12.2022","Pasta","500","12456"}};

    public TransactionHistUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t = new JTable(cells,columns);
        t.setBounds(200,200,200,200);
        JScrollPane p = new JScrollPane(t);
        this.add(p);
        this.setSize(800,800);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TransactionHistUI();
    }

}
