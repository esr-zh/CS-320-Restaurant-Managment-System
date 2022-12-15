package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addEmployeeUI implements ActionListener{
    public static JFrame addEmployeeFrame;
    public static JPanel employeePanel;
    public static JLabel nameLabel, roleLabel, workingHourLabel, contractLabel;
    public static JTextField inputName, inputRole, inputWorkingHour, inputContract;
    public static JButton submitButton;

    public static void generateEmployeeUI() {
        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(null);

        JFrame addEmployeeFrame = new JFrame();
        setFrameProperties(addEmployeeFrame);

        addEmployeeFrame.add(employeePanel);
        nameLabel = new JLabel("Employee Name:");
        nameLabel.setBounds(150, 60, 150, 20);
        employeePanel.add(nameLabel);

        inputName = new JTextField();
        inputName.setBounds(250, 60, 193, 28);
        employeePanel.add(inputName);

        roleLabel = new JLabel("Role:");
        roleLabel.setBounds(150, 120, 150, 20);
        employeePanel.add(roleLabel);

        inputRole = new JTextField();
        inputRole.setBounds(250, 120, 193, 28);
        employeePanel.add(inputRole);

        workingHourLabel = new JLabel("Working Hours:");
        workingHourLabel.setBounds(150, 180, 150, 20);
        employeePanel.add(workingHourLabel);

        inputWorkingHour = new JTextField();
        inputWorkingHour.setBounds(250, 180, 193, 28);
        employeePanel.add(inputWorkingHour);

        contractLabel = new JLabel("Contract Type:");
        contractLabel.setBounds(150, 240, 150, 20);
        employeePanel.add(contractLabel);

        inputContract = new JTextField();
        inputContract.setBounds(250, 240, 193, 28);
        employeePanel.add(inputContract);

        submitButton = new JButton("Submit");
        submitButton.setBounds(300, 300, 90, 25);
        btnProperties(submitButton);
        employeePanel.add(submitButton);

        addEmployeeFrame.setVisible(true);
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
        frame.setTitle("Add New Employee!");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            JOptionPane.showMessageDialog(null, "Added successfully");
        }
    }
}
