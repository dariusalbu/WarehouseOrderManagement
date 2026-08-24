# Warehouse Order Management System (WOMS)

A 3-Tier Java desktop application designed to manage warehouse operations, including client management, product inventory tracking, order processing, and automated bill generation.

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-5432-blue)
![Architecture](https://img.shields.io/badge/Architecture-3--Tier%20%2F%20MVC-green)

---

## Key Features

* **Client Management:** Perform full CRUD operations (Create, Read, Update, Delete) on client records.
* **Product Inventory Management:** Track stock levels, pricing, and product details with automatic stock deduction upon order placement.
* **Order Processing:** Place customer orders with real-time stock verification and validation.
* **Automated Bill Log:** Automatically generates an immutable log entry (`Bill` record) upon successful order creation.
* **Reflection-Based DAO:** A generic data access layer (`AbstractDAO<T>`) utilizing Java Reflection API to execute dynamic SQL queries and automatically map `ResultSet` data to objects.
* **Dynamic Table UI Rendering:** Populates Swing `JTable` components dynamically using reflection.

---

## Tech Stack & Architecture

### Architecture

The application implements a classic **3-Tier / MVC Architecture**:
1. **Presentation Layer (`org.example.presentation`):** Swing GUIs (`ClientGUI`, `ProductGUI`, `WarehouseOrderGUI`, `BillGUI`) and Controllers.
2. **Business Logic Layer (`org.example.bll`):** Business validation rules and interaction coordination (`ClientBLL`, `ProductBLL`, `WarehouseOrderBLL`, `BillBLL`).
3. **Data Access Layer (`org.example.dao`):** Generic CRUD handling using Reflection (`AbstractDAO<T>`) and database connection pooling/management (`ConnectionFactory`).

### Database Model

* **Client:** `id`, `name`, `email`, `age`
* **Product:** `id`, `name`, `price`, `stock`
* **WarehouseOrder:** `id`, `client_id`, `product_id`, `quantity`, `order_date`
* **Bill (Java Record):** `id`, `order_id`, `client_name`, `product_name`, `quantity`, `price`, `date`

---

## Database Setup

1. Make sure you have **PostgreSQL** installed and running on port `5432`.
2. Create a database named `warehouse`.
3. Import the provided database dump file into your PostgreSQL instance:
   ```bash
   psql -U postgres -d warehouse -f dump_warehouse.sql