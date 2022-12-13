import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;

public class Edit_Menu_UI extends Login_UI implements ActionListener{
    // frame
    static JFrame employeeFrame;
    // Table
    static JTable data_table;

    public static JButton editButton, deleteButton;

    // Constructor
    public static void generate_table_ui() {
        {
            // Frame initialization
            employeeFrame = new JFrame();

            // Frame Title
            employeeFrame.setTitle("Edit Menu");

            // Data to be displayed in the JTable
            String[][] data = {
                    {"Drink", "Water", "$0.5", "Edit", "Delete"},
                    {"Dessert", "Cheesecake", "$5", "Edit", "Delete"},
                    {"Dessert", "Cheesecake", "$5", "Edit", "Delete"},
                    {"Dessert", "Cheesecake", "$5", "Edit", "Delete"},
                    {"Dessert", "Cheesecake", "$5", "Edit", "Delete"}
            };

            // Column Names
            String[] columnNames = {"Type", "Name", "Price", "", ""};

            // Initializing the JTable
            data_table = new JTable(data, columnNames);
            data_table.setBounds(30, 40, 200, 300);

            // adding it to JScrollPane
            JScrollPane sp = new JScrollPane(data_table);
            employeeFrame.add(sp);
            // Frame Size
            employeeFrame.setSize(700, 700);
            // Frame Visible = true

            editButton = new JButton("Edit");
            editButton.setBounds(400, 410, 90, 25);
            editButton.setForeground(Color.WHITE);
            editButton.setBackground(Color.BLACK);
            editButton.addActionListener((ActionListener) new Edit_Menu_UI());
            employeeFrame.add(editButton);
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
