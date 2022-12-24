import UI.AddEmployeeUI;
import UI.EditEmployeeUI;
import UI.TransactionHistUI;
import UI.LoginUI;

import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
            //loginUI.generate_login_ui();
            //AddEmployeeUI.generateEmployeeUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //new TransactionHistUI();
        JFrame ownerFrame = new JFrame();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Add Employee",AddEmployeeUI.generateEmployeeUI());
        //tabbedPane.add("Edit Employee", EditEmployeeUI.generate_table_ui());
        ownerFrame.add(tabbedPane);
        ownerFrame.setResizable(false);
        ownerFrame.setSize(700,750);
        ownerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ownerFrame.setVisible(true);

    }
}
