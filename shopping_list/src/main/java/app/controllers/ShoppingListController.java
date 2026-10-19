package app.controllers;

import app.dao.ShoppingListDAO;
import app.dto.ShoppingListDTO;
import app.entities.ShoppingList;
import app.exceptions.APIException;
import io.javalin.http.Context;

import java.util.List;

public class ShoppingListController {
    private final ShoppingListDAO shoppingListDAO;

    public ShoppingListController(ShoppingListDAO shoppingListDAO){
        this.shoppingListDAO = shoppingListDAO;
    }

    public void getAll(Context ctx){
        List<ShoppingListDTO> lists = shoppingListDAO.findAll().stream()
                .map(ShoppingListDTO::fromEntity)
                .toList();
        ctx.json(lists);
    }

    public void getById(Context ctx){
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        ShoppingList list = shoppingListDAO.findById(id)
                .orElseThrow(() -> new APIException(404, "Shopping list with id " + id + " not found"));
        ctx.json(ShoppingListDTO.fromEntity(list));
    }
}
