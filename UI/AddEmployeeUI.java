package UI;
import database.*;
import database.utils.Connect;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

public class AddEmployeeUI extends BasicComboBoxRenderer implements ActionListener {
    public static int yCoordinate = 60;
    public static JPanel employeePanel;
    public static JLabel nameLabel, roleLabel, workingHourLabel, contractLabel, salaryLabel;
    public static JTextField inputName, inputRole, inputWorkingHourFrom, inputWorkingHourTo, inputContract, inputSalary;
    public static JButton submitButton;
    public static JComboBox contractList, toList, fromList;

    public static void generateEmployeeUI() {
        employeePanel = new JPanel();
        employeePanel.setLayout(null);

        JFrame addEmployeeFrame = new JFrame();
        setFrameProperties(addEmployeeFrame);
        addEmployeeFrame.add(employeePanel);

        String[] role = {"Chef", "Waiter"};
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

        inputSalary.setText("$100");

        addComponentToPanel(nameLabel, inputName);
        addRoleDropDown(roleLabel, role);
        addTimeDropDown(workingHourLabel, fromTime, toTime);
        addContractDropDown(contractLabel, contact);
        addComponentToPanel(salaryLabel, inputSalary);

        submitButton = new JButton("Submit");
        submitButton.setBounds(300, 350, 90, 25);
        btnProperties(submitButton);
        employeePanel.add(submitButton);
        addEmployeeFrame.setVisible(true);
    }

    public static void centerWindow(Window frame) {
        Rectangle bounds = frame.getGraphicsConfiguration().getBounds();
        Dimension dimension = bounds.getSize();
        int x = (int) (((dimension.getWidth() - frame.getWidth()) / 2) + bounds.getMinX());
        int y = (int) (((dimension.getHeight() - frame.getHeight()) / 2) + bounds.getMinY());
        frame.setLocation(x, y);
    }

    public static void addTimeDropDown(JLabel label, String[] fromTime, String[] toTime) {
        label.setBounds(150, yCoordinate, 150, 20);
        employeePanel.add(label);
        JLabel to = new JLabel(" to ");
        to.setBounds(330, yCoordinate, 90, 28);
        fromList = new JComboBox<>(fromTime);
        toList = new JComboBox<>(toTime);
        fromList.setBounds(250, yCoordinate, 80, 28);
        toList.setBounds(350, yCoordinate, 90, 28);
        fromList.addActionListener(e -> {
            if (Objects.equals(contractList.getSelectedItem(), "Monthly")) {
                int colonIndex = Objects.requireNonNull(fromList.getSelectedItem()).toString().indexOf(":");
                int eightHour = Integer.parseInt(fromList.getSelectedItem().toString().substring(0, colonIndex)) + 8;
                toList.setSelectedItem((eightHour) + ":00");
                toList.setEnabled(false);
            }
        });
        employeePanel.add(to);
        employeePanel.add(fromList);
        employeePanel.add(toList);
        yCoordinate += 60;
    }

    public static void addComponentToPanel(JLabel label, JTextField textField) {
        label.setBounds(150, yCoordinate, 150, 20);
        employeePanel.add(label);
        textField.setBounds(250, yCoordinate, 193, 28);
        employeePanel.add(textField);
        yCoordinate += 60;
    }

    public static void addRoleDropDown(JLabel label, String[] list) {
        label.setBounds(150, yCoordinate, 150, 20);
        employeePanel.add(label);
        JComboBox<String> roleList = new JComboBox<>(list);
        roleList.addActionListener(e -> {
            String selectedItem = (String) roleList.getSelectedItem();
            if (selectedItem.equals("Waiter")) {
                contractList.setSelectedItem("Hourly");
                contractList.setEnabled(false);
            } else {
                contractList.setEnabled(true);
            }
        });
        roleList.setBounds(250, yCoordinate, 193, 28);
        employeePanel.add(roleList);
        yCoordinate += 60;
    }

    public static void addContractDropDown(JLabel label, String[] list) {
        label.setBounds(150, yCoordinate, 150, 20);
        employeePanel.add(label);
        contractList = new JComboBox<>(list);
        contractList.setBounds(250, yCoordinate, 193, 28);
        contractList.addActionListener(e -> {
            if (Objects.equals(contractList.getSelectedItem(), "Hourly")) {
                toList.setEnabled(true);
            }
            if (contractList.getSelectedItem().equals("Monthly")) {
                int colonIndex = Objects.requireNonNull(fromList.getSelectedItem()).toString().indexOf(":");
                int eightHour = Integer.parseInt(fromList.getSelectedItem().toString().substring(0, colonIndex)) + 8;
                toList.setSelectedItem((eightHour) + ":00");
                toList.setEnabled(false);
            }
        });
        employeePanel.add(contractList);
        yCoordinate += 60;
    }

    private static void btnProperties(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener((ActionListener) new AddEmployeeUI());
    }

    private static void setFrameProperties(JFrame frame) {
        frame.setTitle("Add New Employee!");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 625, 500);
        centerWindow(frame);
        frame.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connect connect = new Connect();
        User user = new User(connect.connection);
        Employee employee = new Employee(connect.connection);
        Shift shift = new Shift(connect.connection);
        UserRole userRole = new UserRole();
        SalaryType salaryType = new SalaryType();
        if (e.getSource() == submitButton) {
            user.setUsername(inputName.getText());
            user.setPassword(inputName.getText());
            shift.setWorkingFrom(Long.parseLong(fromList.getSelectedItem().toString().split(":")[0]));
            shift.setWorkingTo(Long.parseLong(toList.getSelectedItem().toString().split(":")[0]));
            employee.setSalaryType(salaryType.getSalaryType(inputContract.getText()));
            user.setUserRole(userRole.getUserRole(inputRole.getText().toLowerCase(Locale.ROOT)));

            try {
                String salary = inputSalary.getText().substring(1);
                employee.setSalary(Long.parseLong(salary));
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, nfe.getMessage());
            }
            try {
                User newUser = user.createUser();
                employee.setUserId(newUser.getId());
                shift.setUserId(newUser.getId());
            } catch (SQLException ex) {
                System.out.printf(ex.getMessage());
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
                }
            }


        }
    }

