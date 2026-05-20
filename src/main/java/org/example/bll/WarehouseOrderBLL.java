package org.example.bll;

import org.example.dao.WarehouseOrderDAO;
import org.example.model.WarehouseOrder;

public class WarehouseOrderBLL {
    WarehouseOrderDAO orderDao = new WarehouseOrderDAO();

    public WarehouseOrderBLL() {
        orderDao = new WarehouseOrderDAO();
    }

    public WarehouseOrder insert(WarehouseOrder warehouseOrder) {
        return orderDao.insert(warehouseOrder);
    }
}
