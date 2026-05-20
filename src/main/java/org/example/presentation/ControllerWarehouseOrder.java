package org.example.presentation;

import org.example.bll.BillBLL;
import org.example.bll.ClientBLL;
import org.example.bll.ProductBLL;
import org.example.bll.WarehouseOrderBLL;
import org.example.model.Client;
import org.example.model.Product;
import org.example.model.WarehouseOrder;
import org.example.model.Bill;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;

import static org.example.presentation.Controller.populateTable;

public class ControllerWarehouseOrder {
    final WarehouseOrderGUI warehouseOrderGUI;
    final WarehouseOrderBLL warehouseOrderBLL;
    final ProductBLL productBLL;
    final ClientBLL clientBLL;
    final BillBLL billBLL;
    final Controller mainController;

    public ControllerWarehouseOrder(Controller mainController) {
        this.warehouseOrderGUI = new WarehouseOrderGUI();
        this.warehouseOrderBLL = new WarehouseOrderBLL();
        this.mainController = mainController;
        this.productBLL = new ProductBLL();
        this.clientBLL = new ClientBLL();
        this.billBLL = new BillBLL();

        refreshClientTable();
        refreshProductTable();

        this.warehouseOrderGUI.placeOrderButtonListener(e -> placeOrderLogic());
    }

    private void placeOrderLogic() {
        Client client = getSelectedClientFromTable();
        Product product = getSelectedProductFromTable();

        try {
            if (product == null) {
                return;
            }

            int quantity = Integer.parseInt(this.warehouseOrderGUI.getOrderQuantityTextField().getText());

            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be a positive integer");
            }
            if (quantity > product.getStock()) {
                throw new IllegalArgumentException("Insufficient stock");
            }

            WarehouseOrder wareHouseOrder = new WarehouseOrder(client.getId(), product.getId(), quantity, new Timestamp(System.currentTimeMillis()));
            wareHouseOrder = warehouseOrderBLL.insert(wareHouseOrder);

            product.setStock(product.getStock() - quantity);
            productBLL.update(product);

            Bill bill = new Bill(
                    0,
                    wareHouseOrder.getId(),
                    client.getName(),
                    product.getName(),
                    wareHouseOrder.getQuantity(),
                    product.getPrice() * wareHouseOrder.getQuantity(),
                    wareHouseOrder.getOrder_date()
            );

            billBLL.insert(bill);

            mainController.notifyOrderWindow();
            mainController.notifyProductWindow();
            mainController.notifyBillWindow();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(warehouseOrderGUI, "Please enter a valid quantity");
        } catch  (Exception e) {
            JOptionPane.showMessageDialog(warehouseOrderGUI, e.getMessage());
        }
    }

    private Client getSelectedClientFromTable() {
        Client client = null;

        int selectedRow = this.warehouseOrderGUI.getOrdersClientTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a client", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int id = Integer.parseInt(this.warehouseOrderGUI.getOrdersClientTable().getValueAt(selectedRow, 0).toString());
            String name = this.warehouseOrderGUI.getOrdersClientTable().getValueAt(selectedRow, 1).toString();
            String email = this.warehouseOrderGUI.getOrdersClientTable().getValueAt(selectedRow, 2).toString();
            int age = Integer.parseInt(this.warehouseOrderGUI.getOrdersClientTable().getValueAt(selectedRow, 3).toString());

            client = new Client(name, email, age);
            client.setId(id);
        }

        return client;
    }

    private Product getSelectedProductFromTable() {
        Product product = null;

        int selectedRow = this.warehouseOrderGUI.getOrdersProductTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a product", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int id = Integer.parseInt(this.warehouseOrderGUI.getOrdersProductTable().getValueAt(selectedRow, 0).toString());
            String name = this.warehouseOrderGUI.getOrdersProductTable().getValueAt(selectedRow, 1).toString();
            double price = Double.parseDouble(this.warehouseOrderGUI.getOrdersProductTable().getValueAt(selectedRow, 2).toString());
            int stock = Integer.parseInt(this.warehouseOrderGUI.getOrdersProductTable().getValueAt(selectedRow, 3).toString());

            product = new Product(name, price, stock);
            product.setId(id);
        }

        return product;
    }

    public void refreshProductTable() {
        List<Product> products = productBLL.findAll();
        populateTable(this.warehouseOrderGUI.getOrdersProductTable(), products);
    }

    public void refreshClientTable() {
        List<Client> clients = clientBLL.findAll();
        populateTable(this.warehouseOrderGUI.getOrdersClientTable(), clients);
    }
}
