package org.example.presentation;

import org.example.bll.ProductBLL;
import org.example.model.Product;

import javax.swing.*;
import java.util.List;

import static org.example.presentation.Controller.populateTable;

public class ControllerProduct {
    final ProductGUI productGUI;
    final ProductBLL productBLL;

    public ControllerProduct() {
        productGUI = new ProductGUI();
        this.productBLL = new ProductBLL();

        refreshProductTable();
        this.productGUI.addProductButtonListener(e -> addProductWindow());
        this.productGUI.updateProductButtonListener(e -> updateProductWindow());
        this.productGUI.completeAddProductButtonListener(e -> completeAddProduct());
        this.productGUI.completeUpdateProductListener(e -> completeUpdateProduct());
        this.productGUI.getDeleteProductButtonListener(e -> deleteProduct());
    }

    public void addProductWindow() {
        this.productGUI.changeProductWindowCard("addProductCard");
    }

    public void completeAddProduct() {
        try {
            Product  product = getProductDataTextField(
                    this.productGUI.getAddProductNameTextField().getText(),
                    Double.parseDouble(this.productGUI.getAddProductPriceTextField().getText()),
                    Integer.parseInt(this.productGUI.getAddProductStockTextField().getText())
            );

            this.productGUI.changeProductWindowCard("viewProductCard");
            productBLL.insert(product);
            refreshProductTable();

            this.productGUI.getAddProductNameTextField().setText("");
            this.productGUI.getAddProductPriceTextField().setText("");
            this.productGUI.getAddProductStockTextField().setText("");
        }  catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProductWindow() {
        int selectedRow = this.productGUI.getProductTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a product to update", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Product product = getSelectedProductFromTable(selectedRow);

            this.productGUI.getUpdateProductNameTextField().setText(product.getName());
            this.productGUI.getUpdateProductPriceTextField().setText(Double.toString(product.getPrice()));
            this.productGUI.getUpdateProductStockTextField().setText(Integer.toString(product.getStock()));

            this.productGUI.changeProductWindowCard("updateProductCard");
        }
    }

    void completeUpdateProduct() {
        try {
            int selectedRow = this.productGUI.getProductTable().getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a product to update", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int id = Integer.parseInt(this.productGUI.getProductTable().getValueAt(selectedRow, 0).toString());

                Product  product = getProductDataTextField(
                        this.productGUI.getUpdateProductNameTextField().getText(),
                        Double.parseDouble(this.productGUI.getUpdateProductPriceTextField().getText()),
                        Integer.parseInt(this.productGUI.getUpdateProductStockTextField().getText())
                );

                product.setId(id);

                productBLL.update(product);
                refreshProductTable();
                this.productGUI.changeProductWindowCard("viewProductCard");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct() {
        int selectedRow = this.productGUI.getProductTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a product to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Product product = getSelectedProductFromTable(selectedRow);

            productBLL.delete(product);

            refreshProductTable();
        }
    }

    public Product getProductDataTextField(String name, double price, int stock) throws Exception {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Please enter your name");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Please enter a valid price");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Please enter a valid stock");
        }

        return new Product(name, price, stock);
    }

    private Product getSelectedProductFromTable(int selectedRow) {
        int id = Integer.parseInt(this.productGUI.getProductTable().getValueAt(selectedRow, 0).toString());
        String name = this.productGUI.getProductTable().getValueAt(selectedRow, 1).toString();
        double price = Double.parseDouble(this.productGUI.getProductTable().getValueAt(selectedRow, 2).toString());
        int stock = Integer.parseInt(this.productGUI.getProductTable().getValueAt(selectedRow, 3).toString());

        Product product = new Product(name, price, stock);
        product.setId(id);
        return product;
    }

    public void refreshProductTable() {
        List<Product> products = productBLL.findAll();
        populateTable(this.productGUI.getProductTable(), products);
    }
}
