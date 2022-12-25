package UI.UserManager;

import UI.CustomerManager.CustomerMenuUI;
import UI.TransactionHistUI;
import database.User;
import database.UserRole;
import database.utils.Connect;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static UI.NavBar.CustomerNavBar.generateCustomerNavBar;
import static UI.NavBar.OwnerNavBar.generateOwnerNavBar;

public class LoginUI  {
    Connect connect = new Connect();
    User user = new User(connect.connection);
    UserRole userRole = new UserRole();

    public  JLabel password_label, username_label;
    public  JTextField usernameInput;
    public  JButton loginButton, registerButton;
    public  JPasswordField passwordInput;


    public JFrame generateUI() {


        JFrame frame = new JFrame();
        frame.setTitle("LOGIN PAGE");
        frame.setLocation(new Point(500, 300));

        frame.setSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        username_label = new JLabel("Username");
        username_label.setBounds(100, 8, 70, 20);
        panel.add(username_label);

        usernameInput = new JTextField();
        usernameInput.setBounds(100, 27, 193, 28);
        panel.add(usernameInput);

        password_label = new JLabel("Password");
        password_label.setBounds(100, 55, 70, 20);
        panel.add(password_label);

        passwordInput = new JPasswordField();
        passwordInput.setBounds(100, 75, 193, 28);
        panel.add(passwordInput);



        loginButton = new JButton("Login");
        loginButton.setBounds(200, 110, 90, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);




        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setInputValues();
                try {
                    user.authUser();
                    long userId = user.getId();
                    long userRoleNum = user.getUserRole();
                    System.out.println("user id =>" + userId);
                    System.out.println("user role =>" + userRole.getUserRole((int) userRoleNum));
                    if (userRole.getUserRole((int) userRoleNum).equals("customer")){
                        frame.dispose();
                        generateCustomerNavBar(userId);
                    }
                    if (userRole.getUserRole((int) userRoleNum).equals("admin")){
                        frame.dispose();
                        generateOwnerNavBar();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }


        });
        panel.add(loginButton);



        registerButton = new JButton("Register");
        registerButton.setBounds(100, 110, 90, 25);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.BLACK);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInputValues();
                user.setUserRole(1);
                try {
                    user.createUser();
                    long userId = user.getId();
                    JOptionPane.showMessageDialog(null, "Registered Successfully");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(registerButton);
        frame.setVisible(true);
        return frame;
    }

    private void setInputValues() {
        String currentUsername = usernameInput.getText();
        String currentPassword = String.valueOf(passwordInput.getPassword());
        user.setUsername(currentUsername);
        user.setPassword(currentPassword);
    }

}
