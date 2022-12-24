package UI.EmployeeManeger;

import database.*;
import database.utils.Connect;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

import static UI.EmployeeManeger.EmployeeUI.*;

public class EditEmployeeUI2 {
    public static long empUserID;// you have to pass this id first
    public static void generateUI() {
        JButton button = new JButton("Submit");
        button.addActionListener(e -> {
                    try {
                        long userRoleNum = userRole.getUserRole(inputRole.getText());
                        String username = inputName.getText();
                        long salaryTypeNum = salaryType.getSalaryType(Objects.requireNonNull(contractList.getSelectedItem()).toString());
                        System.out.println("this is " + empUserID);
                        employee.setUserId(empUserID);// this is passed from edit employee ui
                        employee.setSalary(Long.parseLong(inputSalary.getText()));
                        employee.setSalaryType(salaryTypeNum);
                        int workingFromIndex = Objects.requireNonNull(fromList.getSelectedItem()).toString().indexOf(":");
                        int workingToIndex = Objects.requireNonNull(toList.getSelectedItem()).toString().indexOf(":");
                        int workingFrom =Integer.parseInt(fromList.getSelectedItem().toString().substring(0,workingFromIndex));
                        int workingTo =Integer.parseInt(toList.getSelectedItem().toString().substring(0,workingToIndex));
                        Shift newShift = new Shift(workingFrom,workingTo);
                        if (employee.updateEmployee(username,userRoleNum,newShift)) {
                            JOptionPane.showMessageDialog(null, "Changes to item have been saved!");
                            EmployeeUI.addEmployeeFrame.dispose();
                            // not the refresh issue but adding a new record
                            new EmployeeUI().generateUI();
                            addEmployeeFrame.setVisible(false);
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        EmployeeUI ui = new EmployeeUI();
        ui.setBtn(button);
        ui.generateUI();
    }
}
