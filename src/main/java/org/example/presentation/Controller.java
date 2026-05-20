package org.example.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Controller {
    private final View view;
    ControllerClient controllerClient;
    ControllerProduct controllerProduct;
    ControllerWarehouseOrder controllerWarehouseOrder;
    ControllerBill controllerBill;


    public Controller() {
        this.view = new View();

        this.view.clientButtonListener(e -> clientWindow());
        this.view.productButtonListener(e -> productWindow());
        this.view.orderButtonListener(e -> orderWindow());
        this.view.billButtonListener(e -> billWindow());
    }

    public void clientWindow() {
        if (this.controllerClient == null) {
            controllerClient = new ControllerClient(this);
        }
        else {
            this.controllerClient.clientGUI.setVisible(true);
        }
    }

    public void productWindow() {
        if (this.controllerProduct == null) {
            controllerProduct = new ControllerProduct(this);
        }
        else {
            this.controllerProduct.productGUI.setVisible(true);
        }
    }

    public void orderWindow() {
        if (this.controllerWarehouseOrder == null) {
            controllerWarehouseOrder = new ControllerWarehouseOrder(this);
        }
        else {
            this.controllerWarehouseOrder.warehouseOrderGUI.setVisible(true);
        }
    }

    public void billWindow() {
        if (this.controllerBill == null) {
            controllerBill = new ControllerBill(this);
        }
        else {
            this.controllerBill.billGUI.setVisible(true);
        }
    }

    public void notifyClientWindow() {
        if (controllerClient != null) {
            controllerClient.refreshClientTable();
        }
    }

    public void notifyProductWindow() {
        if (controllerProduct != null) {
            controllerProduct.refreshProductTable();
        }
    }

    public void notifyOrderWindow() {
        if (controllerWarehouseOrder != null) {
            controllerWarehouseOrder.refreshClientTable();
            controllerWarehouseOrder.refreshProductTable();
        }
    }

    public void notifyBillWindow() {
        if (controllerBill != null) {
            controllerBill.refreshTable();
        }
    }

    public static void populateTable(JTable table, List<?> objects) {
        if (objects == null || objects.isEmpty()) {
            table.setModel(new DefaultTableModel());
            return;
        }

        Class<?> type = objects.get(0).getClass();
        Field[] fields = type.getDeclaredFields();

        Vector<String> columnNames = Arrays.stream(fields)
                .map(Field::getName)
                .collect(Collectors.toCollection(Vector::new));

        Vector<Vector<Object>> data = objects.stream()
                .map(object -> {
                    Vector<Object> row = new Vector<>();

                    Arrays.stream(fields).forEach(field -> {
                        field.setAccessible(true);
                        try {
                            row.add(field.get(object));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });

                    return row;
                })
                .collect(Collectors.toCollection(Vector::new));


        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(model);
    }
}
