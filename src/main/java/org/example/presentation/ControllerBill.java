package org.example.presentation;

import org.example.bll.BillBLL;
import org.example.model.Bill;

import java.util.List;

import static org.example.presentation.Controller.populateTable;

public class ControllerBill {
    final BillGUI billGUI;
    final BillBLL billBLL;

    public ControllerBill() {
        this.billGUI = new BillGUI();
        this.billBLL = new BillBLL();

        refreshTable();
    }

    public void refreshTable() {
        List<Bill> bills = billBLL.findAll();
        populateTable(this.billGUI.getBillTable(), bills);
    }
}
