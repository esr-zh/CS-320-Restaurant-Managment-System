package UI.Tests;

import UI.CustomerManager.CustomerMenuUI;
import UI.CustomerManager.TransactionHistUI;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame mainFrame = new JFrame();
        TransactionHistUI transactionHistUI = new TransactionHistUI();;

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Menu", CustomerMenuUI.generateCustomerUI());
        tabbedPane.add("Transaction History", transactionHistUI.getUIComponent());
        mainFrame.add(tabbedPane);
        mainFrame.setTitle("Welcome");
        mainFrame.setResizable(false);
        mainFrame.setSize(700, 750);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
