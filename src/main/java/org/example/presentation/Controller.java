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
        this.view.getDeleteProductButtonListener(e -> deleteProduct());
        this.view.getDeleteClientButtonListener(e -> deleteClient());
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
        int selectedRow = this.view.getClientTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a client to update", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Client client = getSelectedClientFromTable(selectedRow);

            this.view.getUpdateClientNameTextField().setText(client.getName());
            this.view.getUpdateClientEmailTextField().setText(client.getEmail());
            this.view.getUpdateClientAgeTextField().setText(Integer.toString(client.getAge()));

            this.view.changeClientWindowCard("updateClientCard");
        }
    }

    public Client getClientDataTextField(String name, String email, int age) throws Exception {
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
            Client client = getClientDataTextField(
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
        try {
            int selectedRow = this.view.getClientTable().getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a client to update", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int id = Integer.parseInt(this.view.getClientTable().getValueAt(selectedRow, 0).toString());

                Client client = getClientDataTextField(
                        this.view.getUpdateClientNameTextField().getText(),
                        this.view.getUpdateClientEmailTextField().getText(),
                        Integer.parseInt(this.view.getUpdateClientAgeTextField().getText())
                );

                client.setId(id);

                clientBLL.update(client);
                refreshClientTable();
                this.view.changeClientWindowCard("viewClientCard");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addProductWindow() {
        this.view.changeProductWindowCard("addProductCard");
    }

    public void updateProductWindow() {
        int selectedRow = this.view.getProductTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a product to update", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Product product = getSelectedProductFromTable(selectedRow);

            this.view.getUpdateProductNameTextField().setText(product.getName());
            this.view.getUpdateProductPriceTextField().setText(Double.toString(product.getPrice()));
            this.view.getUpdateProductStockTextField().setText(Integer.toString(product.getStock()));

            this.view.changeProductWindowCard("updateProductCard");
        }
    }

    public Product getProductDataTextField(String name, double price, int stock) throws Exception {
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
            Product  product = getProductDataTextField(
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
        try {
            int selectedRow = this.view.getProductTable().getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a product to update", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int id = Integer.parseInt(this.view.getProductTable().getValueAt(selectedRow, 0).toString());

                Product  product = getProductDataTextField(
                        this.view.getUpdateProductNameTextField().getText(),
                        Double.parseDouble(this.view.getUpdateProductPriceTextField().getText()),
                        Integer.parseInt(this.view.getUpdateProductStockTextField().getText())
                );

                product.setId(id);

                productBLL.update(product);
                refreshProductTable();
                this.view.changeProductWindowCard("viewProductCard");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
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

    public void refreshClientTable() {
        List<Client> clients = clientBLL.findAll();
        populateTable(this.view.getClientTable(), clients);
    }

    public void refreshProductTable() {
        List<Product> products = productBLL.findAll();
        populateTable(this.view.getProductTable(), products);
    }

    public void deleteProduct() {
        int selectedRow = this.view.getProductTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a product to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Product product = getSelectedProductFromTable(selectedRow);

            productBLL.delete(product);

            refreshProductTable();
        }
    }

    private Product getSelectedProductFromTable(int selectedRow) {
        int id = Integer.parseInt(this.view.getProductTable().getValueAt(selectedRow, 0).toString());
        String name = this.view.getProductTable().getValueAt(selectedRow, 1).toString();
        double price = Double.parseDouble(this.view.getProductTable().getValueAt(selectedRow, 2).toString());
        int stock = Integer.parseInt(this.view.getProductTable().getValueAt(selectedRow, 3).toString());

        Product product = new Product(name, price, stock);
        product.setId(id);
        return product;
    }

    public void deleteClient() {
        int selectedRow = this.view.getClientTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a client to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Client client = getSelectedClientFromTable(selectedRow);

            clientBLL.delete(client);

            refreshClientTable();
        }
    }

    private Client getSelectedClientFromTable(int selectedRow) {
        int id = Integer.parseInt(this.view.getClientTable().getValueAt(selectedRow, 0).toString());
        String name = this.view.getClientTable().getValueAt(selectedRow, 1).toString();
        String email = this.view.getClientTable().getValueAt(selectedRow, 2).toString();
        int age = Integer.parseInt(this.view.getClientTable().getValueAt(selectedRow, 3).toString());

        Client client = new Client(name, email, age);
        client.setId(id);
        return client;
    }
}
