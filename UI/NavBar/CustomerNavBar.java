package UI.NavBar;
import UI.CustomerManager.CustomerMenuUI;
import UI.CustomerManager.TransactionHistUI;
import javax.swing.*;
import java.sql.SQLException;

public class CustomerNavBar {
    public static JFrame customerMenuFrame;
    public static void generateCustomerNavBar(long userId) throws SQLException {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        customerMenuFrame = new JFrame();
        TransactionHistUI transactionHistUI = new TransactionHistUI(userId);
        CustomerMenuUI customerMenuUI = new CustomerMenuUI(userId);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Menu", customerMenuUI.generateCustomerUI());
        tabbedPane.add("Transaction History", transactionHistUI.getUIComponent());
        customerMenuFrame.add(tabbedPane);
        customerMenuFrame.setTitle("Customer Menu");
        customerMenuFrame.setResizable(false);
        customerMenuFrame.setSize(700, 750);
        customerMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerMenuFrame.setVisible(true);
    }

}