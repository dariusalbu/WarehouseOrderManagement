package org.example.bll;

import org.example.dao.ProductDAO;
import org.example.model.Product;

import java.util.List;

public class ProductBLL {
    ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }

    public List<Product> findAll() {
        return productDAO.findAll();
    }

    public void insert(Product product) {
        productDAO.insert(product);
    }

    public void update(Product product) {
        productDAO.update(product);
    }
}
