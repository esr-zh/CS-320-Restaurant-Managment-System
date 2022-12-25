package UI.EmployeeManeger;

import database.*;
import database.utils.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class AddEmployeeUI extends EmployeeUI{
    public static Connect connect = new Connect();
    public static Employee employee = new Employee(connect.connection);
    public static User user = new User(connect.connection);

    public static void generateAddUI() {
        EmployeeUI ui = new EmployeeUI();
        JButton button = new JButton("Submit");
        button.addActionListener(e -> {
            Connect connect = new Connect();
            User user = new User(connect.connection);
            Employee employee = new Employee(connect.connection);
            Shift shift = new Shift(connect.connection);
            UserRole userRole = new UserRole();
            SalaryType salaryType = new SalaryType();
            user.setUsername(inputName.getText());
            user.setPassword(inputName.getText());

            long workingFrom = Long.parseLong(fromList.getSelectedItem().toString().split(":")[0]);
            long workingTo = Long.parseLong(toList.getSelectedItem().toString().split(":")[0]);

            if(workingFrom >= workingTo){
                JOptionPane.showMessageDialog(null, "Enter valid time!");
                return;
            }

            shift.setWorkingFrom(workingFrom);
            shift.setWorkingTo(workingTo);
            employee.setSalaryType(salaryType.getSalaryType(contractList.getSelectedItem().toString().toLowerCase()));
            user.setUserRole(userRole.getUserRole(roleList.getSelectedItem().toString().toLowerCase()));

    /*
    System.out.println(inputName.getText());
    System.out.println(userRole.getUserRole(roleList.getSelectedItem().toString().toLowerCase()));
    System.out.println(workingFrom);
    System.out.println(workingTo);
    System.out.println(salaryType.getSalaryType(contractList.getSelectedItem().toString().toLowerCase()));
    System.out.println(Long.parseLong(inputSalary.getText().substring(1)));
    */
            //System.out.println(salaryType.getSalaryType(contractList.getSelectedItem().toString().toLowerCase()));
            try {
                String salary = inputSalary.getText();
                employee.setSalary(Long.parseLong(salary));
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, nfe.getMessage());
                return;
            }
            try {
                User newUser = user.createUser();
                employee.setUserId(newUser.getId());
                shift.setUserId(newUser.getId());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                employee.createEmployee();
                shift.createShiftTime();
                JOptionPane.showMessageDialog(null, "Employee added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                return;
            }
        });
        ui.setBtn(button);
        ui.generateUI();
    }
}
