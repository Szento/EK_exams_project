package app.dao;

import app.entities.ShoppingList;

import java.util.List;

public interface ShoppingListDAO extends GenericDAO<ShoppingList, Long> {
    List<ShoppingList> findByUserId(Long userId);
}
