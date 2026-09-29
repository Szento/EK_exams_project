package app.dao;

import app.entities.Offers;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class OffersDAOImpl extends GenericDAOImpl<Offers, Long> implements OffersDAO {

    public OffersDAOImpl(){
        super(Offers.class);
    }

    public List<Offers> findActiveByStoreId(Long storeId, LocalDate today){
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT o FROM Offers o WHERE o.store.id = :storeId AND o.endDate >= :today", Offers.class)
                    .setParameter("storeId", storeId)
                    .setParameter("today", today)
                    .getResultList();
        }
    }
}
