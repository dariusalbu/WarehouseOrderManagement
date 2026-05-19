package org.example.presentation;

import javax.swing.*;

public class WarehouseOrderGUI extends JFrame {
    private JPanel mainPanel;
    private JScrollPane OrderJScrollPane;

    public WarehouseOrderGUI() {
        this.setContentPane(mainPanel);
        this.setTitle("Order");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
