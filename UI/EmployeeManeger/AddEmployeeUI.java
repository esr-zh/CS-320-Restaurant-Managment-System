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
    public static Shift shift = new Shift(connect.connection);

    public AddEmployeeUI() {
        JButton button = new JButton("Add");
        button.addActionListener(e -> {
            String username = this.getNameInputText();
            user.setUsername(username);
            user.setPassword(username);

            long workingFrom = Long.parseLong(Objects.requireNonNull(fromList.getSelectedItem()).toString().split(":")[0]);
            long workingTo = Long.parseLong(Objects.requireNonNull(toList.getSelectedItem()).toString().split(":")[0]);

//            if(workingFrom >= workingTo){
//                JOptionPane.showMessageDialog(null, "Enter valid time!");
//                return;
//            }

            shift.setWorkingFrom(workingFrom);
            shift.setWorkingTo(workingTo);
            employee.setSalaryType(salaryType.getSalaryType(Objects.requireNonNull(contractList.getSelectedItem()).toString().toLowerCase()));
            user.setUserRole(userRole.getUserRole(Objects.requireNonNull(roleList.getSelectedItem()).toString().toLowerCase()));

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
                return; // to break the code
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                employee.createEmployee();
                shift.createShiftTime();
                JOptionPane.showMessageDialog(null, "Employee added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            this.closeWindow();
        });
        this.setBtn(button);
        this.generateUI();
    }
}
