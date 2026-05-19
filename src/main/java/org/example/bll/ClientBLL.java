package org.example.bll;

import org.example.dao.ClientDAO;
import org.example.model.Client;

import java.util.List;

public class ClientBLL {
    ClientDAO clientDAO;

    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    public Client findById(int id) {
        return clientDAO.findById(id);
    }

    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    public void insert(Client client) {
        clientDAO.insert(client);
    }

    public void update(Client client) {
        clientDAO.update(client);
    }

    public void delete(Client client) {
        clientDAO.delete(client);
    }
}
