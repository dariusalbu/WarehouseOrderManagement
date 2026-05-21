package org.example.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * GUI component for managing Client operations.
 */
public class ClientGUI extends JFrame {
    private JPanel mainPanel;
    private JScrollPane ClientJScrollPane;
    private JPanel clientWindowJPanel;
    private JScrollPane viewClientJScrollPane;
    private JButton deleteClientButton;
    private JButton updateClientButton;
    private JButton addClientButton;
    private JTable clientTable;
    private JScrollPane addClientJScrollPane;
    private JTextField addClientNameTextField;
    private JTextField addClientEmailTextField;
    private JTextField addClientAgeTextField;
    private JButton completeAddClientButton;
    private JScrollPane updateClientJScrollPane;
    private JTextField updateClientNameTextField;
    private JTextField updateClientEmailTextField;
    private JTextField updateClientAgeTextField;
    private JButton completeUpdateClientButton;

    private final CardLayout clientCardLayout;

    public ClientGUI() {
        this.setContentPane(mainPanel);
        this.setTitle("Client");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.clientCardLayout = (CardLayout) clientWindowJPanel.getLayout();
    }

    public void addClientButtonListener(ActionListener listener) {
        addClientButton.addActionListener(listener);
    }

    public void updateClientButtonListener(ActionListener listener) {
        updateClientButton.addActionListener(listener);
    }

    public void completeAddClientButtonListener(ActionListener listener) {
        completeAddClientButton.addActionListener(listener);
    }

    public void completeUpdateClientButtonListener(ActionListener listener) {
        completeUpdateClientButton.addActionListener(listener);
    }

    public JTextField getAddClientNameTextField() {
        return addClientNameTextField;
    }

    public JTextField getAddClientEmailTextField() {
        return addClientEmailTextField;
    }

    public JTextField getAddClientAgeTextField() {
        return addClientAgeTextField;
    }

    public JTable getClientTable() {
        return clientTable;
    }

    public JTextField getUpdateClientNameTextField() {
        return updateClientNameTextField;
    }

    public JTextField getUpdateClientEmailTextField() {
        return updateClientEmailTextField;
    }

    public JTextField getUpdateClientAgeTextField() {
        return updateClientAgeTextField;
    }

    public void getDeleteClientButtonListener(ActionListener listener) {
        deleteClientButton.addActionListener(listener);
    }

    public void changeClientWindowCard(String cardName) {
        clientCardLayout.show(clientWindowJPanel, cardName);
    }
}
