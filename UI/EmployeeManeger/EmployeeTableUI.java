package UI.EmployeeManeger;

import UI.EmployeeManeger.EditEmployeeUI;
import UI.utils.Helper;
import database.Employee;
import database.utils.Connect;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EmployeeTableUI {
    public static Connect connect = new Connect();
    public static Employee employee = new Employee(connect.connection);
    public static JFrame employeeFrame;
    // Table
    public static JTable dataTable;
    public static JButton editButton, deleteButton;
    public static DefaultTableModel tableModel;
    public static void generateUI() {
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

            // Column Names

            String[] columnNames = {"ID", "Name", "Role", "Contract", "Working From","Working To", "Salary"};
            tableModel = new DefaultTableModel(null, columnNames);
            try {
                List<List<String>> results = employee.getAllEmployees();
                for (List<String> row : results) {
                    Object[] insertedRow = new Object[row.size()];
                    for (int i = 0; i < row.size(); i++) {
                        insertedRow[i] = row.get(i);
                    }
                    tableModel.addRow(insertedRow);
                }
                System.out.println(results);
            } catch (Exception e){
                // show error info pop up
            }
            dataTable = new JTable(tableModel);
            dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //prevent a user from selecting multiple rows
            JScrollPane sp = new JScrollPane(dataTable);
            tablePanel.add(sp);

            buttonPanel.add(Box.createHorizontalGlue());
            editButton = new JButton("Edit");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = dataTable.getSelectedRow();
                    if (dataTable.getSelectedRow() != -1) {
                        //edit employee based on ID:
                        EditEmployeeUI2 editEmployeeUI2 = new EditEmployeeUI2(Integer.parseInt(dataTable.getModel().getValueAt(row, 0).toString()));
                        editEmployeeUI2.changeName(dataTable.getModel().getValueAt(row, 1).toString());
                        editEmployeeUI2.roleList.setSelectedItem(dataTable.getModel().getValueAt(row, 2).toString());
                        editEmployeeUI2.contractList.setSelectedItem(dataTable.getModel().getValueAt(row, 3).toString());
                        editEmployeeUI2.fromList.setSelectedItem(dataTable.getModel().getValueAt(row, 4).toString());
                        editEmployeeUI2.toList.setSelectedItem(dataTable.getModel().getValueAt(row, 5).toString());
                        editEmployeeUI2.inputSalary.setText(dataTable.getModel().getValueAt(row, 6).toString());
                    }
                }
            });
            btnProperties(editButton);
            buttonPanel.add(editButton);

            buttonPanel.add(Box.createRigidArea(new Dimension(20, 0))); //space between the two buttons of 20 pixels.

            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = dataTable.getSelectedRow();
                    System.out.println(row);
                    if (dataTable.getSelectedRow() != -1){
                        try {
                            employee.deleteEmployee(dataTable.getModel().getValueAt(row, 1).toString());
                            tableModel.removeRow(dataTable.getSelectedRow());
//                            JOptionPane.showMessageDialog(null, "Selected employee deleted successfully");
                        } catch (SQLException | ClassNotFoundException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                }
            });
            btnProperties(deleteButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(Box.createHorizontalGlue());

            employeeFrame.add(tablePanel, BorderLayout.CENTER);
            employeeFrame.add(buttonPanel, BorderLayout.PAGE_END);
            employeeFrame.pack();
        }
    }
    private static void btnProperties(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private static void setFrameProperties(){
        employeeFrame.setLayout(new BorderLayout());
        employeeFrame.setTitle("Edit Menu");
        employeeFrame.setSize(700, 700);
        Helper.centerWindow(employeeFrame);
        employeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeFrame.setVisible(true);
    }

}
