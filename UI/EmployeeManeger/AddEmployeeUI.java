package UI.EmployeeManeger;

import javax.swing.*;

public class AddEmployeeUI {
    public static void generateUI() {
        JButton submitButton = new JButton("Submit");
        EmployeeUI ui = new EmployeeUI();
        ui.generateUI();
        JFrame frame = ui.getAddEmployeeFrame();
        frame.add(submitButton);

    }

}
