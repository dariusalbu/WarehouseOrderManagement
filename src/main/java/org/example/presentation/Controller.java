package org.example.presentation;

import javax.swing.*;

public class Controller {
    private final View view;

    public Controller() {
        this.view = new View();

        this.view.clientButtonListener(e -> showClientWindow());
        this.view.productButtonListener(e -> showProductWindow());
        this.view.orderButtonListener(e -> showOrderWindow());
        this.view.addClientButtonListener(e -> showAddClientWindow());
        this.view.updateClientButtonListener(e -> showUpdateClientWindow());
        this.view.completeUpdateClientButtonListener(e -> showViewClientWindow());
        this.view.completeAddClientButtonListener(e -> showViewClientWindow());
        this.view.addProductButtonListener(e -> showAddProductWindow());
        this.view.updateProductButtonListener(e -> showUpdateProductWindow());
        this.view.completeAddProductButtonListener(e -> showViewProductWindow());
        this.view.completeUpdateProductListener(e -> showViewProductWindow());
    }

    private void showClientWindow() {
        this.view.changeMainWindowCard("clientCard");
    }

    private void showProductWindow() {
        this.view.changeMainWindowCard("productCard");
    }

    private void showOrderWindow() {
        this.view.changeMainWindowCard("orderCard");
    }

    private void showAddClientWindow() {
        this.view.changeClientWindowCard("addClientCard");
    }

    private void showUpdateClientWindow() {
        this.view.changeClientWindowCard("updateClientCard");
    }

    private void showViewClientWindow() {
        this.view.changeClientWindowCard("viewClientCard");
    }

    private void showAddProductWindow() {
        this.view.changeProductWindowCard("addProductCard");
    }

    private void showUpdateProductWindow() {
        this.view.changeProductWindowCard("updateProductCard");
    }

    private void showViewProductWindow() {
        this.view.changeProductWindowCard("viewProductCard");
    }
}
