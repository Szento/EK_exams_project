package app.dao;

import app.entities.ShoppingList;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ShoppingListDAOImpl extends GenericDAOImpl<ShoppingList, Long> implements ShoppingListDAO{

    public ShoppingListDAOImpl(){
        super(ShoppingList.class);
    }

    @Override
    public List<ShoppingList> findByUserId(Long userId) {
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT s FROM ShoppingList s WHERE s.user.id = :userId", ShoppingList.class)
                    .setParameter("userId", userId)
                    .getResultList();
        }
    }
}
