import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Edit_Menu_UI extends Login_UI implements ActionListener{
    // frame
    static JFrame employeeFrame;
    // Table
    static JTable data_table;

    public static JButton editButton, deleteButton;

    public static DefaultTableModel tableModel;

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

            //tablePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            //buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

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
                    {"Main", "Cheeseburger", "4.99"},
                    {"Drink", "Water", "$0.5"},
                    {"Dessert", "Cheesecake", "$5"},
                    {"Main", "Cheeseburger", "4.99"},
                    {"Drink", "Water", "$0.5"},
                    {"Dessert", "Cheesecake", "$5"},
                    {"Main", "Cheeseburger", "4.99"},
                    {"Drink", "Water", "$0.5"},
                    {"Dessert", "Cheesecake", "$5"},
                    {"Main", "Cheeseburger", "4.99"},
                    {"Drink", "Water", "$0.5"},
                    {"Dessert", "Cheesecake", "$5"},
                    {"Main", "Cheeseburger", "4.99"},
                    {"Dessert", "Cheesecake", "$5"},
                    {"Dessert", "Cheesecake", "$5"},
                    {"Dessert", "Cheesecake", "$5"}
            };
            // Column Names
            String[] columnNames = {"Type", "Name", "Price"};

            // Initializing the JTable
            tableModel = new DefaultTableModel(data, columnNames);
            data_table = new JTable(tableModel);
            data_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //prevent a user from selecting multiple rows
            // adding it to JScrollPane
            JScrollPane sp = new JScrollPane(data_table);
            tablePanel.add(sp);

            buttonPanel.add(Box.createHorizontalGlue());

            editButton = new JButton("Edit");
            editButton.setForeground(Color.WHITE);
            editButton.setBackground(Color.BLACK);
            editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            //editButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            editButton.addActionListener((ActionListener) new Edit_Menu_UI());
            buttonPanel.add(editButton);

            buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            //buttonPanel.add(Box.createHorizontalGlue());

            deleteButton = new JButton("Delete");
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setBackground(Color.BLACK);
            deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            //deleteButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            deleteButton.addActionListener((ActionListener) new Edit_Menu_UI());
            buttonPanel.add(deleteButton);

            buttonPanel.add(Box.createHorizontalGlue());

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
        } else { //else user clicked delete
            if(data_table.getSelectedRow() != -1) {
                // remove selected row from the tableModel
                tableModel.removeRow(data_table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Selected employee deleted successfully");
            }
        }
    }
}
