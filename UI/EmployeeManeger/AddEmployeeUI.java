package UI.EmployeeManeger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeUI {
    public static void generateUI() {
        JButton button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("it works");
            }
        });
        EmployeeUI ui = new EmployeeUI();
        ui.setBtn(button);
        ui.generateUI();

    }

}
