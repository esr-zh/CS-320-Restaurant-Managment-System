package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EditMenu implements ActionListener {
    public static JFrame frame;
    public static JTable table;
    public static JButton editButton, deleteButton;
    public static DefaultTableModel tableModel;

    public static void generateUI(){
        JPanel tablePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        BoxLayout layout1 = new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS);
        BoxLayout layout2 = new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS);

        tablePanel.setLayout(layout1);
        buttonPanel.setLayout(layout2);

        tablePanel.setBorder(BorderFactory.createTitledBorder("Menu Table"));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Select and Manage Menu Items"));

        frame = new JFrame();
        setFrameProperties();

        String[][] data = {
                {"Drink", "Water", "$0.5", "25", "225g", "bubuwefwoifejvjofkeovkeokvkr"},
                {"Dessert", "Cheesecake", "$5", "45", "700g", "nfifniwlfoofnv;svo;;sdl[[[["},
                {"Drink", "Water", "$0.5", "25", "225g", "bubuwefwoifejvjofkeovkeokvkr"},
                {"Dessert", "Cheesecake", "$5", "45", "700g", "nfifniwlfoofnv;svo;;sdl[[[["},
                {"Dessert", "Cheesecake", "$5", "45", "700g", "nfifniwlfoofnv;svo;;sdl[[[["},
                {"Dessert", "Cheesecake", "$5", "45", "700g", "nfifniwlfoofnv;svo;;sdl[[[["},
                {"Dessert", "Cheesecake", "$5", "45", "700g", "nfifniwlfoofnv;svo;;sdl[[[["},
                {"Dessert", "Cheesecake", "$5", "45", "700g", "nfifniwlfoofnv;svo;;sdl[[[["},
                {"Dessert", "Cheesecake", "$5", "45", "700g", "nfifniwlfoofnv;svo;;sdl[[[["},
                {"Dessert", "Cheesecake", "$5", "45", "700g", "nfifniwlfoofnv;svo;;sdl[[[["},


        };

        String[] columnNames = {"Type", "Name", "Price", "Quantity", "Portion", "Description"};
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//prevent a user from selecting multiple rows
        setDimensions();

        JScrollPane sp = new JScrollPane(table);
        tablePanel.add(sp);

        buttonPanel.add(Box.createHorizontalGlue());
        editButton = new JButton("Edit");
        btnProperties(editButton);
        buttonPanel.add(editButton);

        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        deleteButton = new JButton("Delete");
        btnProperties(deleteButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createHorizontalGlue());

        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_END);
        frame.pack();


    }

    public static void setFrameProperties() {
        frame.setLayout(new BorderLayout());
        frame.setTitle("Edit Menu");
        frame.setSize(2400,2000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void btnProperties(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new EditMenu());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editButton) {
            JOptionPane.showMessageDialog(null, "clicked Edit!");
        }

        if (e.getSource() == deleteButton && table.getSelectedRow() != -1){
            tableModel.removeRow(table.getSelectedRow());
            JOptionPane.showMessageDialog(null, "Selected item deleted successfully");

        }
    }

    public static void setDimensions(){
        table.setRowHeight(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(1000);
        table.getColumnModel().getColumn(4).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);
        table.getColumnModel().getColumn(0).setPreferredWidth(300);


    }






}
