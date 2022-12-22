package UI;

import database.Employee;
import database.utils.Connect;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class editEmployeeUI implements ActionListener{
    // frame
    public static JFrame employeeFrame;
    // Table
    public static JTable data_table;

    public static JButton editButton, deleteButton;

    public static DefaultTableModel tableModel;

    public static void generate_table_ui() {
        {
            //used box layout, available at: https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
            //all layouts available at: https://docs.oracle.com/javase/tutorial/uiswing/layout/using.html
            JPanel tablePanel = new JPanel();
            JPanel buttonPanel = new JPanel();

            BoxLayout layout1 = new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS);
            BoxLayout layout2 = new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS);

            tablePanel.setLayout(layout1);
            buttonPanel.setLayout(layout2);

            tablePanel.setBorder(BorderFactory.createTitledBorder("Employee Table"));
            buttonPanel.setBorder(BorderFactory.createTitledBorder("Select and Manage Employees"));

            // Frame initialization
            employeeFrame = new JFrame();
            setFrameProperties();

            // Data to be displayed in the JTable
            String[][] data = {
                    {"1", "Esrah", "Chef", "Monthly", "1:00-8:00", "$499"},
                    {"2", "Mohammad", "Waiter", "Weekly", "3:00-4:00", "100"},
                    {"", "", "", "", "", ""},
                    {"", "", "", "", "", ""},
                    {"", "", "", "", "", ""},
                    {"", "", "", "", "", ""},
            };
            // Column Names
            String[] columnNames = {"ID", "Name", "Role", "Contract", "Working Hours", "Salary"};
            tableModel = new DefaultTableModel(data, columnNames);
            data_table = new JTable(tableModel);
            data_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //prevent a user from selecting multiple rows
            JScrollPane sp = new JScrollPane(data_table);
            tablePanel.add(sp);

            buttonPanel.add(Box.createHorizontalGlue());
            editButton = new JButton("Edit");
            btnProperties(editButton);
            buttonPanel.add(editButton);

            buttonPanel.add(Box.createRigidArea(new Dimension(20, 0))); //space between the two buttons of 20 pixels.

            deleteButton = new JButton("Delete");
            btnProperties(deleteButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(Box.createHorizontalGlue());

            employeeFrame.add(tablePanel, BorderLayout.CENTER);
            employeeFrame.add(buttonPanel, BorderLayout.PAGE_END);
            employeeFrame.pack();
        }
    }

//    public static void generate_table_ui(){
//        employeeFrame = new JFrame();
//        setFrameProperties();
//        String[][] data = {
//                    {"Drink", "Water", "$0.5"},
//                    {"Dessert", "Cheesecake", "$5"},
//                    {"Main", "Cheeseburger", "4.99"},
//                    {"Drink", "Water", "$0.5"},
//                    {"Dessert", "Cheesecake", "$5"},
//                    {"Main", "Cheeseburger", "4.99"},
//                    {"Drink", "Water", "$0.5"},
//                    {"Dessert", "Cheesecake", "$5"},
//                    {"Main", "Cheeseburger", "4.99"},
//                    {"Drink", "Water", "$0.5"},
//                    {"Dessert", "Cheesecake", "$5"},
//                    {"Main", "Cheeseburger", "4.99"},
//                    {"Drink", "Water", "$0.5"},
//                    {"Dessert", "Cheesecake", "$5"},
//                    {"Main", "Cheeseburger", "4.99"},
//                    {"Dessert", "Cheesecake", "$5"},
//                    {"Dessert", "Cheesecake", "$5"},
//                    {"Dessert", "Cheesecake", "$5"}
//            };
//    String[] columnNames = {"Type", "Name", "Price"};
//    TableUI tableUI = new TableUI(data,columnNames);
//    JTable table = tableUI.getTable();
//    employeeFrame.add(table);
//    employeeFrame.add(table, BorderLayout.CENTER);
//    employeeFrame.add(table, BorderLayout.PAGE_END);
//    employeeFrame.pack();
//    }
    private static void btnProperties(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener((ActionListener) new editEmployeeUI());
    }

    private static void setFrameProperties(){
        employeeFrame.setLayout(new BorderLayout());
        employeeFrame.setTitle("Edit Menu");
        employeeFrame.setSize(700, 700);
        employeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connect connect = new Connect();
        Employee employee = new Employee(connect.connection);

        if (e.getSource() == editButton && data_table.getSelectedRow() != -1) {
            //edit employee based on ID:
            int idColumn = 0; //c
            int row = data_table.getSelectedRow();
            String value = data_table.getModel().getValueAt(row, idColumn).toString();
            addEmployeeUI.generateEmployeeUI();
            addEmployeeUI.inputName.setText(data_table.getModel().getValueAt(row, 1).toString());
            addEmployeeUI.roleList.setSelectedItem(data_table.getModel().getValueAt(row, 2).toString());
            addEmployeeUI.contractList.setSelectedItem(data_table.getModel().getValueAt(row, 3).toString());
            addEmployeeUI.inputSalary.setText(data_table.getModel().getValueAt(row, 5).toString());
        }

        if (e.getSource() == deleteButton && data_table.getSelectedRow() != -1){
            tableModel.removeRow(data_table.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Selected employee deleted successfully");

        }
    }
}
