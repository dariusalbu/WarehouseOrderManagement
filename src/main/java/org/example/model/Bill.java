package org.example.model;

import java.security.Timestamp;

public record Bill (
    int id,
    int order_id,
    String client_name,
    String product_name,
    int quantity,
    double price,
    Timestamp date
) {}
