package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class addEmployeeUI implements ActionListener, MouseListener {
    public static int yCoordinate = 60;
    public static JPanel employeePanel;
    public static JLabel nameLabel, roleLabel, workingHourLabel, contractLabel, salaryLabel;
    public static JTextField inputName, inputRole, inputWorkingHourFrom, inputWorkingHourTo, inputContract, inputSalary;
    public static JButton submitButton;
    public static JComboBox contractList;
    public static void generateEmployeeUI() {
        employeePanel = new JPanel();
        employeePanel.setLayout(null);

        JFrame addEmployeeFrame = new JFrame();
        setFrameProperties(addEmployeeFrame);
        addEmployeeFrame.add(employeePanel);

        String[] role = {"Chef","Waiter"};
        String[] contact = {"Monthly", "Hourly"};

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
        addWHComponentToPanel(workingHourLabel, inputWorkingHourFrom, inputWorkingHourTo);
        addContractDropDown(contractLabel, contact);
        addComponentToPanel(salaryLabel, inputSalary);


        submitButton = new JButton("Submit");
        submitButton.setBounds(300, 350, 90, 25);
        btnProperties(submitButton);
        employeePanel.add(submitButton);

        addEmployeeFrame.setVisible(true);
    }

    private static void addWHComponentToPanel(JLabel label, JTextField textField1, JTextField textField2) {
        label.setBounds(150, yCoordinate, 150, 20);
        employeePanel.add(label);
        textField1.setBounds(250, yCoordinate, 95, 28);
        textField1.setText("From");
        textField1.setFont(new Font("From", Font.ITALIC, 12));
        textField1.setForeground(Color.GRAY);
        textField1.addMouseListener(new addEmployeeUI());
        employeePanel.add(textField1);
        textField2.setBounds(350, yCoordinate, 95, 28);
        textField2.setText("To");
        textField2.setFont(new Font("To", Font.ITALIC, 12));
        textField2.setForeground(Color.GRAY);
        textField2.addMouseListener(new addEmployeeUI());
        employeePanel.add(textField2);
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
        JComboBox<String> roleList = new JComboBox<>(list);
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
        employeePanel.add(contractList);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            JOptionPane.showMessageDialog(null, "Employee added successfully!");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==inputWorkingHourFrom){
            inputWorkingHourFrom.setForeground(Color.BLACK);
            inputWorkingHourFrom.setText("");}
        if (e.getSource()==inputWorkingHourTo){
            inputWorkingHourTo.setForeground(Color.BLACK);
            inputWorkingHourTo.setText("");}
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
