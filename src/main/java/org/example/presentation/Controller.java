package org.example.presentation;

import org.example.bll.ClientBLL;
import org.example.bll.ProductBLL;
import org.example.model.Client;
import org.example.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

public class Controller {
    private final View view;

    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;

    public Controller() {
        this.clientBLL = new ClientBLL();
        this.productBLL = new ProductBLL();
        this.view = new View();
        refreshClientTable();
        refreshProductTable();

        this.view.clientButtonListener(e -> clientWindow());
        this.view.productButtonListener(e -> productWindow());
        this.view.orderButtonListener(e -> orderWindow());
        this.view.addClientButtonListener(e -> addClientWindow());
        this.view.updateClientButtonListener(e -> updateClientWindow());
        this.view.completeAddClientButtonListener(e -> completeAddClient());
        this.view.completeUpdateClientButtonListener(e -> completeUpdateClient());
        this.view.addProductButtonListener(e -> addProductWindow());
        this.view.updateProductButtonListener(e -> updateProductWindow());
        this.view.completeAddProductButtonListener(e -> completeAddProduct());
        this.view.completeUpdateProductListener(e -> completeUpdateProduct());

    }

    private void clientWindow() {
        refreshClientTable();
        this.view.changeMainWindowCard("clientCard");
    }

    private void productWindow() {
        refreshProductTable();
        this.view.changeMainWindowCard("productCard");
    }

    private void orderWindow() {
        this.view.changeMainWindowCard("orderCard");
    }

    private void addClientWindow() {
        this.view.changeClientWindowCard("addClientCard");
    }

    private void updateClientWindow() {
        this.view.changeClientWindowCard("updateClientCard");
    }

    void completeAddClient() {
        try {
            this.view.changeClientWindowCard("viewClientCard");

            String name = this.view.getAddClientNameTextField().getText();
            String email = this.view.getAddClientEmailTextField().getText();
            int age = Integer.parseInt(this.view.getAddClientAgeTextField().getText());

            Client client = new Client(name, email, age);

            clientBLL.insert(client);
            refreshClientTable();

            this.view.getAddClientNameTextField().setText("");
            this.view.getAddClientEmailTextField().setText("");
            this.view.getAddClientAgeTextField().setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void completeUpdateClient() {


        this.view.changeClientWindowCard("viewClientCard");
    }

    private void addProductWindow() {
        this.view.changeProductWindowCard("addProductCard");
    }

    private void updateProductWindow() {
        this.view.changeProductWindowCard("updateProductCard");
    }

    void completeAddProduct() {
        try {
            this.view.changeProductWindowCard("viewProductCard");

            String name = this.view.getAddProductNameTextField().getText();
            Double price = Double.valueOf(this.view.getAddProductPriceTextField().getText());
            int currentStock = Integer.parseInt(this.view.getAddProductStockTextField().getText());

            Product product = new Product(name, price, currentStock);

            productBLL.insert(product);
            refreshProductTable();

            this.view.getAddProductNameTextField().setText("");
            this.view.getAddProductPriceTextField().setText("");
            this.view.getAddProductStockTextField().setText("");
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void completeUpdateProduct() {


        this.view.changeProductWindowCard("viewProductCard");
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

    public void refreshClientTable() {
        List<Client> clients = clientBLL.findAll();
        populateTable(this.view.getClientTable(), clients);
    }

    public void refreshProductTable() {
        List<Product> products = productBLL.findAll();
        populateTable(this.view.getProductTable(), products);
    }
}
