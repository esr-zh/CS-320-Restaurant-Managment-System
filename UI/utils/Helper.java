package UI.utils;

import UI.EmployeeManeger.EmployeeTableUI;
import database.Employee;
import database.SalaryType;
import database.UserRole;
import database.utils.Connect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class Helper {
    private static Connect connect = new Connect();
    public static Employee employee = new Employee(connect.connection);
    public static  UserRole userRole = new UserRole();
    public static SalaryType salaryType = new SalaryType();
    public static void centerWindow(Window frame) {
        Rectangle bounds = frame.getGraphicsConfiguration().getBounds();
        Dimension dimension = bounds.getSize();
        int x = (int) (((dimension.getWidth() - frame.getWidth()) / 2) + bounds.getMinX());
        int y = (int) (((dimension.getHeight() - frame.getHeight()) / 2) + bounds.getMinY());
        frame.setLocation(x, y);
    }
    public static void loadTableData() {
        EmployeeTableUI.tableModel.setRowCount(0);
        try {
            java.util.List<java.util.List<String>> results = employee.getAllEmployees();
            System.out.println(results);
            for (List<String> row : results) {
                Object[] insertedRow = new Object[row.size()];
                for (int i = 0; i < row.size(); i++) {
                    if(i == 2){// user role
                        insertedRow[i] = userRole.getUserRole(Integer.parseInt(row.get(i)));
                    }
                    else if (i == 3){ // contract / shift / salary type
                        insertedRow[i] = salaryType.getSalaryType((Integer.parseInt(row.get(i))));
                    }else {
                        insertedRow[i] = row.get(i);
                    }
                }
                EmployeeTableUI.tableModel.addRow(insertedRow);
            }
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
