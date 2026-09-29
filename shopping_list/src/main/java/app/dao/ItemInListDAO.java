package app.dao;

import app.entities.ItemInList;

import java.util.Optional;

public interface ItemInListDAO extends GenericDAO<ItemInList, Long> {
    Optional<ItemInList> findByListAndProduct(Long shoppingListId, Long productId);
}
