package app.dao;

import app.entities.Product;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductDAOImpl extends GenericDAOImpl<Product, Long> implements ProductDAO {

    public ProductDAOImpl(){
        super(Product.class);
    }

    @Override
    public List<Product> findByStoreId(Long storeId){
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT p FROM Product p WHERE p.store.id = :storeId", Product.class)
                    .setParameter("storeId", storeId)
                    .getResultList();
        }
    }


}
