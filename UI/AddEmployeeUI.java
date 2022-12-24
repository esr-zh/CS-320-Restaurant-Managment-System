package UI;
import UI.utils.Helper;
import database.*;
import database.utils.Connect;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class AddEmployeeUI extends BasicComboBoxRenderer{

    public static Connect connect = new Connect();
    public static Employee employee = new Employee(connect.connection);
    public static User user = new User(connect.connection);
    public static JFrame addEmployeeFrame = new JFrame();
    public static long empUserID;
    public static SalaryType salaryType = new SalaryType();
    public static UserRole userRole = new UserRole();
    public static int yCoordinate = 60;
    public static JPanel employeePanel;
    public static JLabel nameLabel, roleLabel, workingHourLabel, contractLabel, salaryLabel;
    public static JTextField inputName, inputRole, inputWorkingHourFrom, inputWorkingHourTo, inputContract, inputSalary;
    public static JButton submitButton;
    public static JComboBox<String> contractList, roleList, toList, fromList;
    public static void generateEmployeeUI(){
        employeePanel = new JPanel();
        employeePanel.setLayout(null);
        setFrameProperties(addEmployeeFrame);
        addEmployeeFrame.add(employeePanel);

        String[] role = {"Chef","Waiter"};
        String[] contact = {"Hourly", "Monthly"};
        String[] fromTime = {"1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"};
        String[] toTime = {"1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"};
        nameLabel = new JLabel("Employee Name:");
        inputName = new JTextField();
        roleLabel = new JLabel(" Employee Role:");
        inputRole = new JTextField();
        workingHourLabel = new JLabel("Working Hours:");
        inputWorkingHourFrom = new JTextField();
        inputWorkingHourTo = new JTextField();
        contractLabel = new JLabel("Contract Type:");
        inputContract = new JTextField();
        salaryLabel = new JLabel("Salary:");
        inputSalary = new JTextField();

        inputSalary.setText("100");

        addComponentToPanel(nameLabel, inputName);
        addRoleDropDown(roleLabel, role);
        addTimeDropDown(workingHourLabel, fromTime, toTime);
        addContractDropDown(contractLabel, contact);
        addComponentToPanel(salaryLabel, inputSalary);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employee.setSalary(Long.parseLong(inputSalary.getText()));
                // continue working here
                JOptionPane.showMessageDialog(null, "Employee added successfully!");

            }
        });
        submitButton.setBounds(300, 350, 90, 25);
        submitButton.addActionListener(e -> {
            try {
                // i will run the program and you will see what i mena
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
                    AddEmployeeUI.addEmployeeFrame.dispose();
                    AddEmployeeUI.generateEmployeeUI();
                    addEmployeeFrame.setVisible(false);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        );
        btnProperties(submitButton);
        employeePanel.add(submitButton);
        addEmployeeFrame.setVisible(true);
    }

    public static void addTimeDropDown(JLabel label, String[] fromTime, String[] toTime){
        label.setBounds(150, yCoordinate, 150, 20);
        employeePanel.add(label);
        JLabel to = new JLabel(" to ");
        to.setBounds(330, yCoordinate, 90, 28);
        fromList = new JComboBox<>(fromTime);
        toList = new JComboBox<>(toTime);
        fromList.setBounds(250, yCoordinate, 80, 28);
        toList.setBounds(350, yCoordinate, 90, 28);
        fromList.addActionListener(e -> {
            if(Objects.equals(contractList.getSelectedItem(), "Monthly")){
                int colonIndex = Objects.requireNonNull(fromList.getSelectedItem()).toString().indexOf(":");
                int eightHour=Integer.parseInt(fromList.getSelectedItem().toString().substring(0,colonIndex))+8;
                toList.setSelectedItem((eightHour)+":00");
                toList.setEnabled(false);
            }
        });
        employeePanel.add(to);
        employeePanel.add(fromList);
        employeePanel.add(toList);
        yCoordinate+=60;
    }

    public static void addComponentToPanel(JLabel label, JTextField textField){
        label.setBounds(150, yCoordinate, 150, 20);
        employeePanel.add(label);
        textField.setBounds(250, yCoordinate, 193, 28);
        employeePanel.add(textField);
        yCoordinate+=60;
    }

    public static void addRoleDropDown(JLabel label, String[] list){
        label.setBounds(150, yCoordinate, 150, 20);
        employeePanel.add(label);
        roleList = new JComboBox<>(list);
        roleList.addActionListener(e -> {
            String selectedItem = (String) roleList.getSelectedItem();
            if(selectedItem.equals("Waiter"))
            {
                contractList.setSelectedItem("Hourly");
                contractList.setEnabled(false);
            }else{
                contractList.setEnabled(true);
            }
        });
        roleList.setBounds(250, yCoordinate, 193, 28);
        employeePanel.add(roleList);
        yCoordinate+=60;
    }
    public static void addContractDropDown(JLabel label, String[] list){
        label.setBounds(150, yCoordinate, 150, 20);
        employeePanel.add(label);
        contractList = new JComboBox<>(list);
        contractList.setBounds(250, yCoordinate, 193, 28);
        contractList.addActionListener(e -> {
            if (Objects.equals(contractList.getSelectedItem(), "Hourly")){
                toList.setEnabled(true);
            }
            if(contractList.getSelectedItem().equals("Monthly")){
                int colonIndex = Objects.requireNonNull(fromList.getSelectedItem()).toString().indexOf(":");
                int eightHour=Integer.parseInt(fromList.getSelectedItem().toString().substring(0,colonIndex))+8;
                toList.setSelectedItem((eightHour)+":00");
                toList.setEnabled(false);
            }
        });
        employeePanel.add(contractList);
        yCoordinate+=60;
    }

    private static void btnProperties(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
//        button.addActionListener((ActionListener) new AddEmployeeUI());
    }

    private static void setFrameProperties(JFrame frame){
        frame.setTitle("Add New Employee!");
        frame.setSize(700, 700);
        yCoordinate = 60;
        frame.setBounds(10,10,625,500);
        Helper.centerWindow(frame);
        frame.setResizable(false);

    }

}
