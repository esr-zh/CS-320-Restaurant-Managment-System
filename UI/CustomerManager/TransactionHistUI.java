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
    Connect connect = new Connect();
    TransactionHistory transactionHistory = new TransactionHistory(connect.connection);
    JTable table;
    String[] columns = {"Transaction ID","Name","Price","Quantity","Total Price"};
    DefaultTableModel model = new DefaultTableModel(null,columns);
    long userId;
    JFrame customerMenuFrame;

    public TransactionHistUI(long userId) {
        // we call db statements
        List<List<String>> result;
        try {
            result = transactionHistory.getTransactionHistoryTableByUserId((int) userId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        double value = 0;
        System.out.println(result);
        boolean isFirstRow = true; //set flag to check if it is first row
        String currentId = ""; //to check whether the ID is same or not
        double totalGroupPrice = 0;
        for (List<String> row : result) {
            Object[] insertedRow = new Object[row.size() + 1];
            value += Double.parseDouble( row.get(2)) * Double.parseDouble(row.get(3));
            for (int i = 0; i < row.size(); i++) {
                insertedRow[i] = row.get(i);
            }
            //adds a row if it's not in the first row and the ID is changed
            if(!isFirstRow && !row.get(0).equals(currentId)){
                model.addRow(new Object[]{null, null, null, "Total", totalGroupPrice});
                totalGroupPrice = 0; // after adding total price row for current transaction, group price set to 0
            }
            currentId = row.get(0);
            double totalPrice = Double.parseDouble(row.get(2)) * Double.parseDouble(row.get(3)); //total price for one row
            totalGroupPrice += totalPrice;
            insertedRow[row.size()] = totalPrice; //add total price to last column
            model.addRow(insertedRow);

            isFirstRow = false;// flag set to false after first row
        }
        model.addRow(new Object[]{null,null,null,"Total",totalGroupPrice});
        model.addRow(new Object[]{null,null,null,"Total of all transactions",value});

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
    public void setMainFrame(JFrame customerMenuFrame) {
        this.customerMenuFrame = customerMenuFrame;
    }
}