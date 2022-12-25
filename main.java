import UI.CustomerManager.CustomerMenuUI;
import UI.EmployeeManager.EmployeeTableUI;
import UI.UserManager.LoginUI;

import javax.swing.*;

public class main {
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }

        new LoginUI().generateUI();

    }
}
