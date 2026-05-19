package org.example.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductGUI extends JFrame {
    private JPanel mainPanel;
    private JScrollPane ProductJScrollPane;
    private JPanel productWindowJPanel;
    private JScrollPane viewProductJScrollPane;
    private JButton deleteProductButton;
    private JButton updateProductButton;
    private JButton addProductButton;
    private JTable productTable;
    private JScrollPane addProductJScrollPane;
    private JTextField addProductNameTextField;
    private JTextField addProductPriceTextField;
    private JTextField addProductStockTextField;
    private JButton completeAddProductButton;
    private JScrollPane updateProductJScrollPane;
    private JTextField updateProductNameTextField;
    private JTextField updateProductPriceTextField;
    private JTextField updateProductStockTextField;
    private JButton completeUpdateProductButton;

    private final CardLayout productCardLayout;

    public ProductGUI() {
        this.setContentPane(mainPanel);
        this.setTitle("Product");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.productCardLayout = (CardLayout) productWindowJPanel.getLayout();
    }

    public void addProductButtonListener(ActionListener listener) {
        addProductButton.addActionListener(listener);
    }

    public void updateProductButtonListener(ActionListener listener) {
        updateProductButton.addActionListener(listener);
    }

    public void completeAddProductButtonListener(ActionListener listener) {
        completeAddProductButton.addActionListener(listener);
    }

    public void completeUpdateProductListener(ActionListener listener) {
        completeUpdateProductButton.addActionListener(listener);
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

    public JTable getProductTable() {
        return productTable;
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

    public void getDeleteProductButtonListener(ActionListener listener) {
        deleteProductButton.addActionListener(listener);
    }

    public void changeProductWindowCard(String cardName) {
        productCardLayout.show(productWindowJPanel, cardName);
    }
}
