package app.dto;

import app.entities.ShoppingList;

public record ShoppingListDTO(Long id, String name, double overallPrice, Long userId) {
    public static ShoppingListDTO fromEntity(ShoppingList list){
        Long userId = list.getUser() != null ? list.getUser().getId() : null;

        return new ShoppingListDTO(list.getId(), list.getName(), list.getOverallPrice(), userId);
    }

}
