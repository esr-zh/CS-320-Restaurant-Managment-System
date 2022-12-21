import UI.*;
import javax.swing.*;

public class main {
    public static void main(String args[]) {
        //loginUI.generate_login_ui();
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
            CustomerMenuUI.generateCustomerUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
