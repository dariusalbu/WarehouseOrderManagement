package org.example.model;

import java.sql.Timestamp;

public class WarehouseOrder {
    private int id;
    private int client_id;
    private int product_id;
    private int quantity;
    private Timestamp order_date;

    public WarehouseOrder(int client_id, int product_id, int quantity, Timestamp order_date) {
        this.client_id = client_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.order_date = order_date;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
    public Timestamp getOrder_date() {
        return order_date;
    }
}
