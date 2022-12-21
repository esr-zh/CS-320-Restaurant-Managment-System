import UI.AddEmployeeUI;
import UI.TransactionHistUI;
import UI.loginUI;

import javax.swing.*;

public class main {
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
            //loginUI.generate_login_ui();
            AddEmployeeUI.generateEmployeeUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //new TransactionHistUI();
    }
}
