package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton editButton;
    private JButton deleteButton;

    public TableUI(String[][] data, String[] columnNames) {
        // Data to be displayed in the JTable
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //prevent a user from selecting multiple rows
        JScrollPane sp = new JScrollPane(table);

        // Frame initialization
        frame = new JFrame();
        setFrameProperties();

        JPanel tablePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        BoxLayout layout1 = new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS);
        BoxLayout layout2 = new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS);

        tablePanel.setLayout(layout1);
        buttonPanel.setLayout(layout2);

        tablePanel.setBorder(BorderFactory.createTitledBorder("Employee Table"));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Select and Manage Employees"));

        tablePanel.add(sp);

        buttonPanel.add(Box.createHorizontalGlue());
        editButton = new JButton("Edit");
        btnProperties(editButton);
        buttonPanel.add(editButton);

        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0))); //space between the two buttons of 20 pixels.

        deleteButton = new JButton("Delete");
        btnProperties(deleteButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createHorizontalGlue());

        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_END);
        frame.pack();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public void setEditButton(JButton editButton) {
        this.editButton = editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public void setFrameProperties() {
        //set other frame properties here
    }

    public void btnProperties(JButton button) {
        //set button properties here
    }
}

