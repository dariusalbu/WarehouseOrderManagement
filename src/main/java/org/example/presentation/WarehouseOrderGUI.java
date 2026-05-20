package org.example.presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class WarehouseOrderGUI extends JFrame {
    private JPanel mainPanel;
    private JTable clientTable;
    private JTable productTable;
    private JTextField orderQuantityTextField;
    private JButton placeOrderButton;
    private JLabel Client;

    public WarehouseOrderGUI() {
        this.setContentPane(mainPanel);
        this.setTitle("Order");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void placeOrderButtonListener(ActionListener listener) {
        placeOrderButton.addActionListener(listener);
    }

    public JTable getOrdersClientTable() {
        return clientTable;
    }

    public JTable getOrdersProductTable() {
        return productTable;
    }

    public JTextField getOrderQuantityTextField() {
        return orderQuantityTextField;
    }
}
