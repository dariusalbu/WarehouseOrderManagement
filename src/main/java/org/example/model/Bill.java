package org.example.model;

import java.sql.Timestamp;

/**
 * Data model representing a Bill.
 */
public record Bill (
    int id,
    int order_id,
    String client_name,
    String product_name,
    int quantity,
    double price,
    Timestamp date
) {}
