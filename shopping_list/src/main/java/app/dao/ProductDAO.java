package app.dao;

import app.entities.Product;

import java.util.List;

public interface ProductDAO extends GenericDAO<Product, Long> {
    List<Product> findByStoreId(Long storeId);
}
