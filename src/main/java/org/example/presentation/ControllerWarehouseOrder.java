package org.example.presentation;

import org.example.bll.WarehouseOrderBLL;

public class ControllerWarehouseOrder {
    final WarehouseOrderGUI warehouseOrderGUI;
    final WarehouseOrderBLL warehouseOrderBLL;

    public ControllerWarehouseOrder() {
        this.warehouseOrderGUI = new WarehouseOrderGUI();
        this.warehouseOrderBLL = new WarehouseOrderBLL();
    }
}
