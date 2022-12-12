import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_UI extends main implements ActionListener {
    public static JLabel password_label, username_label;
    public static JTextField username_input;
    public static JButton login_button, register_button;
    public static JPasswordField password_input;

    public static void generate_login_ui() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JFrame frame = new JFrame();
        frame.setTitle("RMS - Welcome");
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
        login_button.setBounds(100, 110, 90, 25);
        login_button.setForeground(Color.WHITE);
        login_button.setBackground(Color.BLACK);
        login_button.addActionListener((ActionListener) new Login_UI());
        panel.add(login_button);

        register_button = new JButton("Register");
        register_button.setBounds(200, 110, 90, 25);
        register_button.setForeground(Color.WHITE);
        register_button.setBackground(Color.BLACK);
        register_button.addActionListener((ActionListener) new Login_UI());
        panel.add(register_button);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login_button) {
            String current_username = username_input.getText().trim();
            String current_password = String.valueOf(password_input.getPassword());
            if (current_username.equals("admin") && current_password.equals("password")) {
                JOptionPane.showMessageDialog(null, "Login Successful");
                Edit_Menu_UI.generate_table_ui();

            }else
                JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
        }
        else {
            JOptionPane.showMessageDialog(null, "Registered Successfully");
        }
    }
}
