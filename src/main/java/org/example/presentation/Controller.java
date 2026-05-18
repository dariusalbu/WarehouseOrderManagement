package org.example.presentation;

import org.example.model.Client;

import javax.swing.*;

public class Controller {
    private final View view;

    public Controller() {
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

        this.view.changeClientWindowCard("viewClientCard");
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


        this.view.changeProductWindowCard("viewProductCard");
    }

    void completeUpdateProduct() {


        this.view.changeProductWindowCard("viewProductCard");
    }
}
