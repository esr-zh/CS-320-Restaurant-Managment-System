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
    static JFrame frame;
    // Table
    static JTable data_table;

    // Constructor
    public static void generate_table_ui() {
        {
            // Frame initialization
            frame = new JFrame();

            // Frame Title
            frame.setTitle("Edit Menu");


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
            frame.add(sp);
            // Frame Size
            frame.setSize(1000, 700);
            // Frame Visible = true
            frame.setVisible(true);

            register_button = new JButton("Register");
            register_button.setBounds(200, 110, 90, 25);
            register_button.setForeground(Color.WHITE);
            register_button.setBackground(Color.BLACK);
            register_button.addActionListener((ActionListener) new Login_UI());
            //panel.add(register_button);
            frame.setVisible(true);
        }

    }
}
