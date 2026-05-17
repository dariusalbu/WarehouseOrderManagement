package org.example.presentation;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel mainPanel;
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    private JButton button4;
    private JPanel mainWindowJPanel;
    private JScrollPane mainJScrollPane;
    private JButton button1;
    private JButton button2;

    private final CardLayout cardLayout;

    public View() {
        this.setContentPane(mainPanel);
        this.setTitle("Orders Management");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.cardLayout = (CardLayout) mainWindowJPanel.getLayout();
    }

    public void addClientButtonListener(ActionListener listener) {
        clientButton.addActionListener(listener);
    }

    public void addProductButtonListener(ActionListener listener) {
        productButton.addActionListener(listener);
    }

    public void addOrderButtonListener(ActionListener listener) {
        orderButton.addActionListener(listener);
    }

    public void changeMainWindowCard(String cardName) {
        cardLayout.show(mainWindowJPanel, cardName);
    }
}
