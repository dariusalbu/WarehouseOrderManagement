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

    public void clientWindow() {
        refreshClientTable();
        this.view.changeMainWindowCard("clientCard");
    }

    public void productWindow() {
        refreshProductTable();
        this.view.changeMainWindowCard("productCard");
    }

    public void orderWindow() {
        this.view.changeMainWindowCard("orderCard");
    }

    public void addClientWindow() {
        this.view.changeClientWindowCard("addClientCard");
    }

    public void updateClientWindow() {
        this.view.changeClientWindowCard("updateClientCard");
    }

    public Client getClientData(String name, String email, int age) throws Exception {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Please enter your name");
        }
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Please enter your email");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Please enter a valid age");
        }

        return new Client(name, email, age);
    }

    public void completeAddClient() {
        try {
            Client client = getClientData(
                    this.view.getAddClientNameTextField().getText(),
                    this.view.getAddClientEmailTextField().getText(),
                    Integer.parseInt(this.view.getAddClientAgeTextField().getText())
            );

            this.view.changeClientWindowCard("viewClientCard");
            clientBLL.insert(client);
            refreshClientTable();

            this.view.getAddClientNameTextField().setText("");
            this.view.getAddClientEmailTextField().setText("");
            this.view.getAddClientAgeTextField().setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void completeUpdateClient() {


        this.view.changeClientWindowCard("viewClientCard");
    }

    public void addProductWindow() {
        this.view.changeProductWindowCard("addProductCard");
    }

    public void updateProductWindow() {
        this.view.changeProductWindowCard("updateProductCard");
    }

    public Product getProductData(String name, double price, int stock) throws Exception {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Please enter your name");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Please enter a valid price");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Please enter a valid stock");
        }

        return new Product(name, price, stock);
    }

    public void completeAddProduct() {
        try {
            Product  product = getProductData(
                    this.view.getAddProductNameTextField().getText(),
                    Double.parseDouble(this.view.getAddProductPriceTextField().getText()),
                    Integer.parseInt(this.view.getAddProductStockTextField().getText())
            );

            this.view.changeProductWindowCard("viewProductCard");
            productBLL.insert(product);
            refreshProductTable();

            this.view.getAddProductNameTextField().setText("");
            this.view.getAddProductPriceTextField().setText("");
            this.view.getAddProductStockTextField().setText("");
        }  catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
