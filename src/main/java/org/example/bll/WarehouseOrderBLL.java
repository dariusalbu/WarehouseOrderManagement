package org.example.bll;

import org.example.dao.WarehouseOrderDAO;
import org.example.model.WarehouseOrder;

/**
 * BLL component for managing Order operations.
 */
public class WarehouseOrderBLL {
    WarehouseOrderDAO orderDao = new WarehouseOrderDAO();

    public WarehouseOrderBLL() {
        orderDao = new WarehouseOrderDAO();
    }

    public WarehouseOrder insert(WarehouseOrder warehouseOrder) {
        return orderDao.insert(warehouseOrder);
    }
}
