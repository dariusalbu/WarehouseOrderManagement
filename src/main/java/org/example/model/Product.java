package org.example.model;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private int stock;

    public Product() {
    }

    public Product(String name, Double price, int currentStock) {
        this.name = name;
        this.price = price;
        this.stock = currentStock;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
