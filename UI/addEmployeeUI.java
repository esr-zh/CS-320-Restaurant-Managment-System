package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addEmployeeUI implements ActionListener{
    public static int yCoordinate = 60;
    public static JPanel employeePanel;
    public static JLabel nameLabel, roleLabel, workingHourLabel, contractLabel;
    public static JTextField inputName, inputRole, inputWorkingHour, inputContract;
    public static JButton submitButton;
    public static void generateEmployeeUI() {
        employeePanel = new JPanel();
        employeePanel.setLayout(null);

        JFrame addEmployeeFrame = new JFrame();
        setFrameProperties(addEmployeeFrame);
        addEmployeeFrame.add(employeePanel);

        nameLabel = new JLabel("Employee Name:");
        inputName = new JTextField();
        roleLabel = new JLabel("Role:");
        inputRole = new JTextField();
        workingHourLabel = new JLabel("Working Hours:");
        inputWorkingHour = new JTextField();
        contractLabel = new JLabel("Contract Type:");
        inputContract = new JTextField();

        addComponentToPanel(nameLabel, inputName);
        addComponentToPanel(roleLabel, inputRole);
        addComponentToPanel(workingHourLabel, inputWorkingHour);
        addComponentToPanel(contractLabel, inputContract);

        submitButton = new JButton("Submit");
        submitButton.setBounds(300, 300, 90, 25);
        btnProperties(submitButton);
        employeePanel.add(submitButton);

        addEmployeeFrame.setVisible(true);
    }

    public static void addComponentToPanel(JLabel label, JTextField textField){
        label.setBounds(150, yCoordinate, 150, 20);
        employeePanel.add(label);
        textField.setBounds(250, yCoordinate, 193, 28);
        employeePanel.add(textField);
        yCoordinate+=60;
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
