package UI;

import database.User;
import database.utils.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginUI implements ActionListener {

    public static JLabel password_label, username_label;
    public static JTextField username_input;
    public static JButton login_button, register_button;
    public static JPasswordField password_input;

    public static void generate_login_ui() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JFrame frame = new JFrame();
        frame.setTitle("LOGIN PAGE");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        username_label = new JLabel("Username");
        username_label.setBounds(100, 8, 70, 20);
        panel.add(username_label);

        username_input = new JTextField();
        username_input.setBounds(100, 27, 193, 28);
        panel.add(username_input);

        password_label = new JLabel("Password");
        password_label.setBounds(100, 55, 70, 20);
        panel.add(password_label);

        password_input = new JPasswordField();
        password_input.setBounds(100, 75, 193, 28);
        panel.add(password_input);


        login_button = new JButton("Login");
        login_button.setBounds(200, 110, 90, 25);
        login_button.setForeground(Color.WHITE);
        login_button.setBackground(Color.BLACK);

        login_button.addActionListener((ActionListener) new LoginUI());

        panel.add(login_button);

        register_button = new JButton("Register");
        register_button.setBounds(100, 110, 90, 25);
        register_button.setForeground(Color.WHITE);
        register_button.setBackground(Color.BLACK);
        register_button.addActionListener((ActionListener) new LoginUI());
        panel.add(register_button);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Connect connect;
        connect = new Connect();
        User user = new User(connect.connection);
        String current_username = username_input.getText().trim();
        String current_password = String.valueOf(password_input.getPassword());
        if (e.getSource() == login_button) {
            user.setUsername(current_username);
            user.setPassword(current_password);
            try {
                user.authUser();
                long userId = user.getId();
                JOptionPane.showMessageDialog(null, "Login Successful");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }

        if (e.getSource() == register_button){
            user.setUsername(current_username);
            user.setPassword(current_password);
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
    }
}
