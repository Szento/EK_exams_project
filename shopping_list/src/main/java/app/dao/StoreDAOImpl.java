package app.dao;

import app.entities.Store;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class StoreDAOImpl extends GenericDAOImpl<Store, Long> implements StoreDAO {

    public StoreDAOImpl(){
        super(Store.class);
    }

    @Override
    public Optional<Store> findByName(String name){
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT s FROM Store s WHERE s.name = :name", Store.class)
                    .setParameter("name", name)
                    .getResultStream()
                    .findFirst();
        }
    }
}
