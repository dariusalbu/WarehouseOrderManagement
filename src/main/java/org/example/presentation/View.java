package org.example.presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel mainPanel;
    private JButton clientWindowButton;
    private JButton productWindowButton;
    private JButton orderWindowButton;
    private JButton billWindowButton;

    public View() {
        this.setContentPane(mainPanel);
        this.setTitle("Warehouse");
        this.setSize(300, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void clientButtonListener(ActionListener listener) {
        clientWindowButton.addActionListener(listener);
    }

    public void productButtonListener(ActionListener listener) {
        productWindowButton.addActionListener(listener);
    }

    public void orderButtonListener(ActionListener listener) {
        orderWindowButton.addActionListener(listener);
    }

    public void billButtonListener(ActionListener listener) {
        billWindowButton.addActionListener(listener);}
}
