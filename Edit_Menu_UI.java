import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Edit_Menu_UI extends Login_UI implements ActionListener{
    // frame
    static JFrame employeeFrame;
    // Table
    static JTable data_table;

    public static JButton editButton, deleteButton;

    public static void generate_table_ui() {
        {
            //used boxLayout: reference: https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
            //all layouts available at: https://docs.oracle.com/javase/tutorial/uiswing/layout/using.html
            JPanel tablePanel = new JPanel();
            JPanel buttonPanel = new JPanel();

            BoxLayout layout1 = new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS);
            BoxLayout layout2 = new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS);
            tablePanel.setLayout(layout1);
            buttonPanel.setLayout(layout2);

            tablePanel.setBorder(BorderFactory.createTitledBorder("Employee Table"));
            buttonPanel.setBorder(BorderFactory.createTitledBorder("Select and Manage Employees"));
            //panel.setLayout(null);

            // Frame initialization
            employeeFrame = new JFrame();
            employeeFrame.setLayout(new BorderLayout());
            JFrame.setDefaultLookAndFeelDecorated(true);
            employeeFrame.setTitle("Edit Menu");
            employeeFrame.setSize(700, 700);
            employeeFrame.add(tablePanel);
            employeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Data to be displayed in the JTable
            String[][] data = {
                    {"Drink", "Water", "$0.5"},
                    {"Dessert", "Cheesecake", "$5"},
                    {"Dessert", "Cheesecake", "$5"},
                    {"Dessert", "Cheesecake", "$5"},
                    {"Dessert", "Cheesecake", "$5"}
            };

            // Column Names
            String[] columnNames = {"Type", "Name", "Price"};

            // Initializing the JTable
            data_table = new JTable(data, columnNames);
            data_table.setBounds(30, 40, 200, 300);
            // adding it to JScrollPane
            JScrollPane sp = new JScrollPane(data_table);
            tablePanel.add(sp);

            editButton = new JButton("Edit");
            editButton.setForeground(Color.WHITE);
            editButton.setBackground(Color.BLACK);
            editButton.addActionListener((ActionListener) new Edit_Menu_UI());
            editButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            buttonPanel.add(editButton);

            deleteButton = new JButton("Delete");
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setBackground(Color.BLACK);
            deleteButton.addActionListener((ActionListener) new Edit_Menu_UI());
            deleteButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            buttonPanel.add(deleteButton);

            employeeFrame.add(tablePanel, BorderLayout.CENTER);
            employeeFrame.add(buttonPanel, BorderLayout.PAGE_END);
            employeeFrame.pack();
            employeeFrame.setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            JOptionPane.showMessageDialog(null, "clicked Edit!");
        } else {
            JOptionPane.showMessageDialog(null, "Clicked delete!");
        }
    }
}
