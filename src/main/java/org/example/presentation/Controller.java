package org.example.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

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
            controllerClient = new ControllerClient();
        }
        else {
            this.controllerClient.clientGUI.setVisible(true);
        }
    }

    public void productWindow() {
        if (this.controllerProduct == null) {
            controllerProduct = new ControllerProduct();
        }
        else {
            this.controllerProduct.productGUI.setVisible(true);
        }
    }

    public void orderWindow() {
        if (this.controllerWarehouseOrder == null) {
            controllerWarehouseOrder = new ControllerWarehouseOrder();
        }
        else {
            this.controllerWarehouseOrder.warehouseOrderGUI.setVisible(true);
        }
    }

    public void billWindow() {
        if (this.controllerBill == null) {
            controllerBill = new ControllerBill();
        }
        else {
            this.controllerBill.billGUI.setVisible(true);
        }
    }

    public static void populateTable(JTable table, List<?> objects) {
        if (objects == null || objects.isEmpty()) {
            table.setModel(new DefaultTableModel());
            return;
        }

        Class<?> type = objects.get(0).getClass();
        Field[] fields = type.getDeclaredFields();

        Vector<String> columnNames = new Vector<>();
        for (Field field : fields) {
            columnNames.add(field.getName());
        }

        Vector<Vector<Object>> data = new Vector<>();
        for (Object object : objects) {
            Vector<Object> row = new Vector<>();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    row.add(field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            data.add(row);
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(model);
    }
}
