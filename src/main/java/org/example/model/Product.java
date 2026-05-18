package org.example.model;

public class Product {
    private int id;
    private String name;
    private Double price;
    private int stock;

    public Product(String name, Double price, int currentStock) {
        this.name = name;
        this.price = price;
        this.stock = currentStock;
    }
}
