import UI.OwnerMenu;

import javax.swing.*;
import java.sql.SQLException;

public class main {
    public static void main(String args[]) throws SQLException {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        OwnerMenu.generateUI();
    }
}
