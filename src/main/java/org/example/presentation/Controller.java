package org.example.presentation;

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
        this.view.completeUpdateClientButtonListener(e -> viewClientWindow());
        this.view.completeAddClientButtonListener(e -> viewClientWindow());
        this.view.addProductButtonListener(e -> addProductWindow());
        this.view.updateProductButtonListener(e -> updateProductWindow());
        this.view.completeAddProductButtonListener(e -> viewProductWindow());
        this.view.completeUpdateProductListener(e -> viewProductWindow());
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

    private void viewClientWindow() {
        this.view.changeClientWindowCard("viewClientCard");
    }

    private void addProductWindow() {
        this.view.changeProductWindowCard("addProductCard");
    }

    private void updateProductWindow() {
        this.view.changeProductWindowCard("updateProductCard");
    }

    private void viewProductWindow() {
        this.view.changeProductWindowCard("viewProductCard");
    }
}
