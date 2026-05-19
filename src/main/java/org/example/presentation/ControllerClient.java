package org.example.presentation;

import org.example.bll.ClientBLL;
import org.example.model.Client;

import javax.swing.*;
import java.util.List;

import static org.example.presentation.Controller.populateTable;

public class ControllerClient {
    final ClientGUI clientGUI;
    final ClientBLL clientBLL;

    public ControllerClient() {
        this.clientGUI = new ClientGUI();
        this.clientBLL = new ClientBLL();

        refreshClientTable();
        this.clientGUI.addClientButtonListener(e -> addClientWindow());
        this.clientGUI.updateClientButtonListener(e -> updateClientWindow());
        this.clientGUI.completeAddClientButtonListener(e -> completeAddClient());
        this.clientGUI.completeUpdateClientButtonListener(e -> completeUpdateClient());
        this.clientGUI.getDeleteClientButtonListener(e -> deleteClient());
    }

    public void addClientWindow() {
        this.clientGUI.changeClientWindowCard("addClientCard");
    }

    public void completeAddClient() {
        try {
            Client client = getClientDataTextField(
                    this.clientGUI.getAddClientNameTextField().getText(),
                    this.clientGUI.getAddClientEmailTextField().getText(),
                    Integer.parseInt(this.clientGUI.getAddClientAgeTextField().getText())
            );

            this.clientGUI.changeClientWindowCard("viewClientCard");
            clientBLL.insert(client);
            refreshClientTable();

            this.clientGUI.getAddClientNameTextField().setText("");
            this.clientGUI.getAddClientEmailTextField().setText("");
            this.clientGUI.getAddClientAgeTextField().setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateClientWindow() {
        int selectedRow = this.clientGUI.getClientTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a client to update", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Client client = getSelectedClientFromTable(selectedRow);

            this.clientGUI.getUpdateClientNameTextField().setText(client.getName());
            this.clientGUI.getUpdateClientEmailTextField().setText(client.getEmail());
            this.clientGUI.getUpdateClientAgeTextField().setText(Integer.toString(client.getAge()));

            this.clientGUI.changeClientWindowCard("updateClientCard");
        }
    }

    public void completeUpdateClient() {
        try {
            int selectedRow = this.clientGUI.getClientTable().getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a client to update", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int id = Integer.parseInt(this.clientGUI.getClientTable().getValueAt(selectedRow, 0).toString());

                Client client = getClientDataTextField(
                        this.clientGUI.getUpdateClientNameTextField().getText(),
                        this.clientGUI.getUpdateClientEmailTextField().getText(),
                        Integer.parseInt(this.clientGUI.getUpdateClientAgeTextField().getText())
                );

                client.setId(id);

                clientBLL.update(client);
                refreshClientTable();
                this.clientGUI.changeClientWindowCard("viewClientCard");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteClient() {
        int selectedRow = this.clientGUI.getClientTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a client to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Client client = getSelectedClientFromTable(selectedRow);

            clientBLL.delete(client);

            refreshClientTable();
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

    private Client getSelectedClientFromTable(int selectedRow) {
        int id = Integer.parseInt(this.clientGUI.getClientTable().getValueAt(selectedRow, 0).toString());
        String name = this.clientGUI.getClientTable().getValueAt(selectedRow, 1).toString();
        String email = this.clientGUI.getClientTable().getValueAt(selectedRow, 2).toString();
        int age = Integer.parseInt(this.clientGUI.getClientTable().getValueAt(selectedRow, 3).toString());

        Client client = new Client(name, email, age);
        client.setId(id);
        return client;
    }

    public void refreshClientTable() {
        List<Client> clients = clientBLL.findAll();
        populateTable(this.clientGUI.getClientTable(), clients);
    }
}
