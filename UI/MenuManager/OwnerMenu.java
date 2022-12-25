package UI.MenuManager;

import database.Menu;
import database.utils.Connect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OwnerMenu implements ActionListener {
    public static Connect connect = new Connect();
    public static Menu menu = new Menu(connect.connection);
    static JPanel thisPanel;
    public static JTable table;
    public static JButton editButton, deleteButton, addButton;
    public static DefaultTableModel tableModel;

    public static Component generateUI() throws SQLException {
        JPanel tablePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        BoxLayout layout1 = new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS);
        BoxLayout layout2 = new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS);

        tablePanel.setLayout(layout1);
        buttonPanel.setLayout(layout2);

        tablePanel.setBorder(BorderFactory.createTitledBorder("Menu Table"));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Select and Manage Menu Items"));

        thisPanel = new JPanel();
        thisPanel.setLayout(new BorderLayout());

        String[] columnNames = {"Id","Type", "Name", "Price", "Quantity", "Portion", "Description"};
        tableModel = new DefaultTableModel(null, columnNames);

        List<List<String>> result = menu.listAllMenu();

        for(List<String> row : result){
            Object[] insertedRow = new Object[row.size()];
            for (int i = 0; i < row.size(); i++) {
                insertedRow[i] = row.get(i);
            }
            tableModel.addRow(insertedRow);
        }
        System.out.println(result);

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//prevent a user from selecting multiple rows

        JScrollPane sp = new JScrollPane(table);
        tablePanel.add(sp);

        buttonPanel.add(Box.createHorizontalGlue());
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                AddMenu.generateUI();
            }

        });
        btnProperties(addButton);
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                long productId = Integer.parseInt(tableModel.getValueAt(selectedRow,0).toString());
                String productType = (String) tableModel.getValueAt(selectedRow,1);
                String productName = (String) tableModel.getValueAt(selectedRow,2);
                String productPrice = (String) tableModel.getValueAt(selectedRow,3);
                String productQuantity = (String) tableModel.getValueAt(selectedRow,4);
                String productPortion = (String) tableModel.getValueAt(selectedRow,5);
                String productDesc = (String) tableModel.getValueAt(selectedRow,6);
                System.out.println(productType);
                EditMenu.generateUI();
                EditMenu.itemId = productId;
                EditMenu.productType.setSelectedItem(productType);
                EditMenu.productName.setText(productName);
                EditMenu.productPrice.setText(productPrice);
                EditMenu.productQuantity.setText(productQuantity);
                EditMenu.portionText.setText(productPortion);
                EditMenu.descText.setText(productDesc);
            }
        });
        btnProperties(editButton);
        buttonPanel.add(editButton);


        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        deleteButton = new JButton("Delete");
        btnProperties(deleteButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createHorizontalGlue());



        thisPanel.add(tablePanel, BorderLayout.CENTER);
        thisPanel.add(buttonPanel, BorderLayout.PAGE_END);

        return thisPanel;
    }
    private static void btnProperties(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new OwnerMenu());
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == deleteButton && table.getSelectedRow() != -1){
            int selectedRow = table.getSelectedRow();
            long productId = Integer.parseInt(tableModel.getValueAt(selectedRow,0).toString());
            menu.setId(productId);
            try {
                if (menu.deleteMenu()){
                    tableModel.removeRow(table.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected item deleted successfully");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
    public static void centerWindow(Window frame) {
        Rectangle bounds = frame.getGraphicsConfiguration().getBounds();
        Dimension dimension = bounds.getSize();
        int x = (int) (((dimension.getWidth() - frame.getWidth()) / 2) + bounds.getMinX());
        int y = (int) (((dimension.getHeight() - frame.getHeight()) / 2) + bounds.getMinY());
        frame.setLocation(x, y);
    }

}
