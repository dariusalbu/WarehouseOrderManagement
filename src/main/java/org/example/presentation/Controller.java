package org.example.presentation;

import org.example.bll.ClientBLL;
import org.example.bll.ProductBLL;
import org.example.model.Client;
import org.example.model.Product;

import javax.swing.*;
import java.sql.SQLException;

public class Controller {
    private final View view;

    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;

    public Controller() {
        this.clientBLL = new ClientBLL();
        this.productBLL = new ProductBLL();
        this.view = new View();

        this.view.clientButtonListener(e -> clientWindow());
        this.view.productButtonListener(e -> productWindow());
        this.view.orderButtonListener(e -> orderWindow());
        this.view.addClientButtonListener(e -> addClientWindow());
        this.view.updateClientButtonListener(e -> updateClientWindow());
        this.view.completeAddClientButtonListener(e -> completeAddClient());
        this.view.completeUpdateClientButtonListener(e -> completeUpdateClient());
        this.view.addProductButtonListener(e -> addProductWindow());
        this.view.updateProductButtonListener(e -> updateProductWindow());
        this.view.completeAddProductButtonListener(e -> completeAddProduct());
        this.view.completeUpdateProductListener(e -> completeUpdateProduct());
    }

    private void clientWindow() {
        this.view.changeMainWindowCard("clientCard");
    }

    private void productWindow() {
        this.view.changeMainWindowCard("productCard");
    }

    private void orderWindow() {
        this.view.changeMainWindowCard("orderCard");
    }

    private void addClientWindow() {
        this.view.changeClientWindowCard("addClientCard");
    }

    private void updateClientWindow() {
        this.view.changeClientWindowCard("updateClientCard");
    }

    void completeAddClient() {
        try {
            this.view.changeClientWindowCard("viewClientCard");

            String name = this.view.getAddClientNameTextField().getText();
            String email = this.view.getAddClientEmailTextField().getText();
            int age = Integer.parseInt(this.view.getAddClientAgeTextField().getText());

            Client client = new Client(name, email, age);

            clientBLL.insert(client);

            this.view.getAddClientNameTextField().setText("");
            this.view.getAddClientEmailTextField().setText("");
            this.view.getAddClientAgeTextField().setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void completeUpdateClient() {


        this.view.changeClientWindowCard("viewClientCard");
    }

    private void addProductWindow() {
        this.view.changeProductWindowCard("addProductCard");
    }

    private void updateProductWindow() {
        this.view.changeProductWindowCard("updateProductCard");
    }

    void completeAddProduct() {
        try {
            this.view.changeProductWindowCard("viewProductCard");

            String name = this.view.getAddProductNameTextField().getText();
            Double price = Double.valueOf(this.view.getAddProductPriceTextField().getText());
            int currentStock = Integer.parseInt(this.view.getAddProductStockTextField().getText());

            Product product = new Product(name, price, currentStock);

            productBLL.insert(product);

            this.view.getAddProductNameTextField().setText("");
            this.view.getAddProductPriceTextField().setText("");
            this.view.getAddProductStockTextField().setText("");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void completeUpdateProduct() {


        this.view.changeProductWindowCard("viewProductCard");
    }
}
