package org.example.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel mainPanel;
    private JButton clientWindowButton;
    private JButton productWindowButton;
    private JButton orderWindowButton;
    private JPanel mainWindowJPanel;
    private JScrollPane mainJScrollPane;
    private JScrollPane ClientJScrollPane;
    private JScrollPane ProductJScrollPane;
    private JScrollPane OrderJScrollPane;
    private JButton deleteProductButton;
    private JButton updateProductButton;
    private JButton addProductButton;
    private JTable table2;
    private JButton deleteClientButton;
    private JButton updateClientButton;
    private JButton addClientButton;
    private JTable table1;
    private JButton completeAddClientButton;
    private JTextField addClientNameTextField;
    private JTextField addClientEmailTextField;
    private JTextField addClientAgeTextField;
    private JScrollPane addClientJScrollPane;
    private JScrollPane updateClientJScrollPane;
    private JScrollPane viewClientJScrollPane;
    private JPanel clientWindowJPanel;
    private JPanel productWindowJPanel;
    private JScrollPane viewProductJScrollPane;
    private JScrollPane updateProductJScrollPane;
    private JScrollPane addProductJScrollPane;
    private JButton completeUpdateClientButton;
    private JButton completeUpdateProductButton;
    private JButton completeAddProductButton;
    private JTextField addProductNameTextField;
    private JTextField addProductPriceTextField;
    private JTextField addProductStockTextField;
    private JTextField updateProductNameTextField;
    private JTextField updateProductPriceTextField;
    private JTextField updateProductStockTextField;
    private JTextField updateClientNameTextField;
    private JTextField updateClientEmailTextField;
    private JTextField updateClientAgeTextField;

    private final CardLayout mainCardLayout;
    private final CardLayout clientCardLayout;
    private final CardLayout productCardLayout;

    public View() {
        this.setContentPane(mainPanel);
        this.setTitle("Orders Management");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainCardLayout = (CardLayout) mainWindowJPanel.getLayout();
        this.clientCardLayout = (CardLayout) clientWindowJPanel.getLayout();
        this.productCardLayout = (CardLayout) productWindowJPanel.getLayout();
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

    public void addClientButtonListener(ActionListener listener) {
        addClientButton.addActionListener(listener);
    }

    public void updateClientButtonListener(ActionListener listener) {
        updateClientButton.addActionListener(listener);
    }

    public void addProductButtonListener(ActionListener listener) {
        addProductButton.addActionListener(listener);
    }

    public void updateProductButtonListener(ActionListener listener) {
        updateProductButton.addActionListener(listener);
    }

    public void completeAddClientButtonListener(ActionListener listener) {
        completeAddClientButton.addActionListener(listener);
    }

    public void completeUpdateClientButtonListener(ActionListener listener) {
        completeUpdateClientButton.addActionListener(listener);
    }

    public void completeAddProductButtonListener(ActionListener listener) {
        completeAddProductButton.addActionListener(listener);
    }

    public void completeUpdateProductListener(ActionListener listener) {
        completeUpdateProductButton.addActionListener(listener);
    }

    public void changeMainWindowCard(String cardName) {
        mainCardLayout.show(mainWindowJPanel, cardName);
    }

    public void changeClientWindowCard(String cardName) {
        clientCardLayout.show(clientWindowJPanel, cardName);
    }

    public void changeProductWindowCard(String cardName) {
        productCardLayout.show(productWindowJPanel, cardName);
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

    public JTextField getAddProductNameTextField() {
        return addProductNameTextField;
    }

    public JTextField getAddProductPriceTextField() {
        return addProductPriceTextField;
    }

    public JTextField getAddProductStockTextField() {
        return addProductStockTextField;
    }

    public JTextField getUpdateProductNameTextField() {
        return updateProductNameTextField;
    }

    public JTextField getUpdateProductPriceTextField() {
        return updateProductPriceTextField;
    }

    public JTextField getUpdateProductStockTextField() {
        return updateProductStockTextField;
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
}
