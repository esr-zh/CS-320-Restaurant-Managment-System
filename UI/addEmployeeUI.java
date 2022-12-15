package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class addEmployeeUI {
    public static JFrame addEmployeeFrame;
    public static JPanel employeePanel;
    public static JLabel nameLabel, roleLabel, workingHourLabel, contractLabel;
    public static JTextField inputName, inputRole, inputWorkingHour, inputContract;
    public static JButton submitButton;

    public static void  generateEmployeeUI() {
        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(null);

        JFrame addEmployeeFrame = new JFrame();
        addEmployeeFrame.setTitle("test");
        addEmployeeFrame.setSize(200,200);
        setFrameProperties(addEmployeeFrame);

        addEmployeeFrame.add(employeePanel);
        nameLabel = new JLabel("Employee Name:");
        nameLabel.setBounds(100, 8, 70, 20);
        employeePanel.add(nameLabel);

        inputName = new JTextField();
        inputName.setBounds(100, 27, 193, 28);
        employeePanel.add(inputName);

        roleLabel = new JLabel("Role:");
        roleLabel.setBounds(100, 55, 70, 20);
        employeePanel.add(roleLabel);

        inputName = new JTextField();
        inputName.setBounds(100, 83, 193, 28);
        employeePanel.add(inputName);

        nameLabel = new JLabel("Working Hours:");
        nameLabel.setBounds(100, 111, 70, 20);
        employeePanel.add(nameLabel);

        inputName = new JTextField();
        inputName.setBounds(100, 27, 193, 28);
        employeePanel.add(inputName);

        roleLabel = new JLabel("Contract Type:");
        roleLabel.setBounds(100, 55, 70, 20);
        employeePanel.add(roleLabel);

        inputName = new JTextField();
        inputName.setBounds(100, 83, 193, 28);
        employeePanel.add(inputName);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 110, 90, 25);
        btnProperties(submitButton);
        employeePanel.add(submitButton);
    }

    private static void addForm(){
    }


    private static void btnProperties(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener((ActionListener) new addEmployeeUI());
    }

    private static void setFrameProperties(JFrame frame){
        //addEmployeeFrame.setLayout();
        frame.setTitle("Add New Employee!");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
