package UI.EmployeeManeger;

import database.*;
import database.utils.Connect;
import UI.utils.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.util.List;
import java.util.Objects;

import static UI.EmployeeManeger.EmployeeUI.*;

public class EditEmployeeUI2 extends EmployeeUI {
    private long empUserID;// you have to pass this id first

    public long getEmpUserID() {
        return empUserID;
    }

    public void setEmpUserID(long empUserID) {
        System.out.println("passed => " + empUserID);
        this.empUserID = empUserID;
    }

    public EditEmployeeUI2(long x) {
        JButton button = new JButton("Submit");
        employee.setUserId(x);// this is passed from edit employee ui
        button.addActionListener(e -> { // we had a scope issue here for emp user id
                    try {
                        long userRoleNum = userRole.getUserRole(inputRole.getText());
                        String username = getNameInputText();
                        long salaryTypeNum = salaryType.getSalaryType(Objects.requireNonNull(contractList.getSelectedItem()).toString());
                        employee.setSalary(Long.parseLong(inputSalary.getText()));
                        employee.setSalaryType(salaryTypeNum);
                        int workingFromIndex = Objects.requireNonNull(fromList.getSelectedItem()).toString().indexOf(":");
                        int workingToIndex = Objects.requireNonNull(toList.getSelectedItem()).toString().indexOf(":");
                        int workingFrom =Integer.parseInt(fromList.getSelectedItem().toString().substring(0,workingFromIndex));
                        int workingTo =Integer.parseInt(toList.getSelectedItem().toString().substring(0,workingToIndex));
                        Shift newShift = new Shift(workingFrom,workingTo);
                        if (employee.updateEmployee(username,userRoleNum,newShift)) {
                            Helper.loadTableData();
                            this.closeWindow();
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                });
        this.setBtn(button);
        this.generateUI();
    }


}
