package org.example.presentation;

import javax.swing.*;

public class Controller {
    private final View view;

    public Controller() {
        this.view = new View();

        this.view.addClientButtonListener(e -> handleClient());
        this.view.addProductButtonListener(e -> handleProduct());
        this.view.addOrderButtonListener(e -> handleOrder());
    }

    private void handleClient() {
        this.view.changeMainWindowCard("ClientCard");
    }

    private void handleProduct() {
        this.view.changeMainWindowCard("ProductCard");
    }

    private void handleOrder() {
        this.view.changeMainWindowCard("OrderCard");
    }
}
