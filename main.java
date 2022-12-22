import UI.*;

import javax.swing.*;

public class main {
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        EditEmployeeUI.generateUI();
    }
}
