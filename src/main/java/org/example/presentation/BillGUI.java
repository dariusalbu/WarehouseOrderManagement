package org.example.presentation;

import javax.swing.*;

/**
 * GUI component for displaying the Bills table.
 */
public class BillGUI extends JFrame {
    private JPanel mainPanel;
    private JTable billTable;

    public BillGUI() {
        this.setContentPane(mainPanel);
        this.setTitle("Bill");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JTable getBillTable() {
        return billTable;
    }
}
