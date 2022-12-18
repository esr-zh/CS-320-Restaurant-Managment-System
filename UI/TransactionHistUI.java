package UI;

import database.TransactionHistory;
import database.utils.Connect;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TransactionHistUI extends JFrame {
    Connection connection;
    TransactionHistory transactionHistory;
    JTable table;
    String[] columns = {"Transaction ID","Name","Price","Quantity","Total Price"};
    DefaultTableModel model = new DefaultTableModel(null,columns);


    public TransactionHistUI() {
        // we call db statements
        Connect connect = new Connect();
        connection = connect.connection;
        transactionHistory = new TransactionHistory(connection);
        List<List<String>> result;
        try {
            result = transactionHistory.getTransactionHistoryTableByUserId(4);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
        for (List<String> row : result) {
            Object[] insertedRow = new Object[row.size()];
            for (int i = 0; i < row.size(); i++) {
                insertedRow[i] = row.get(i);
            }
            model.addRow(insertedRow);
        }


        model.setColumnIdentifiers(columns);

    }

    public JScrollPane getUIComponent(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table = new JTable(model);
        System.out.println(model.getDataVector());
        table.setBounds(200,200,200,200);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Transaction History Table"));
        return scrollPane;
    }

    public static void main(String args[]){
        JMenu menu, TH;
        JFrame frame = new JFrame("Menu and MenuItem Example");

        TransactionHistUI transactionHistUI = new TransactionHistUI();;
        frame.add(transactionHistUI.getUIComponent());

        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Menu");
        TH = new JMenu("Transaction History");

        mb.add(menu);
        mb.add(TH);
        frame.setJMenuBar(mb);

        frame.setSize(800,800);
        frame.setVisible(true);

    }


}
