package app.dao;

import app.entities.ItemInList;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class ItemInListDAOImpl extends GenericDAOImpl<ItemInList, Long> implements ItemInListDAO {

    public ItemInListDAOImpl() {
        super(ItemInList.class);
    }

    @Override
    public Optional<ItemInList> findByListAndProduct(Long shoppingListId, Long productId){
        try(EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT i FROM ItemInList i WHERE i.shoppingList.id = :listId AND i.product.id = :productId", ItemInList.class)
                    .setParameter("listId", shoppingListId)
                    .setParameter("productId", productId)
                    .getResultStream()
                    .findFirst();
        }
    }
}
