import UI.EmployeeManeger.AddEmployeeUI;
import UI.EmployeeManeger.EmployeeTableUI;

import javax.swing.*;

public class main {
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        EmployeeTableUI.generateUI();
//        EmployeeTableUI.generateUI();
//        new AddEmployeeUI();
    }
}
