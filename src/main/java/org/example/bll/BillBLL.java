package org.example.bll;

import org.example.dao.BillDAO;
import org.example.model.Bill;

import java.util.List;

public class BillBLL {
    BillDAO billDAO = new BillDAO();

    public BillBLL() {
        billDAO = new BillDAO();
    }

    public void insert(Bill bill) {
        billDAO.insert(bill);
    }

    public List<Bill> findAll() {
        return billDAO.findAll();
    }
}
