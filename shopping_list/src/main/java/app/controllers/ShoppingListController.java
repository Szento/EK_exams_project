package app.controllers;

import app.dao.ShoppingListDAO;
import app.dao.UserDAO;
import app.dto.ShoppingListDTO;
import app.entities.ShoppingList;
import app.entities.User;
import app.exceptions.APIException;
import io.javalin.http.Context;

import java.util.List;

public class ShoppingListController {
    private final ShoppingListDAO shoppingListDAO;
    private final UserDAO userDAO;

    public ShoppingListController(ShoppingListDAO shoppingListDAO, UserDAO userDAO){
        this.shoppingListDAO = shoppingListDAO;
        this.userDAO = userDAO;
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
    public void create(Context ctx){
        ShoppingListDTO dto = ctx.bodyValidator(ShoppingListDTO.class)
                .check(d -> d.name() != null && !d.name().isBlank(), "name is required")
                .check(d -> d.overallPrice() >= 0, "overallPrice cannot be negative")
                .check(d -> d.userId() != null, "user needs id")
                .get();

        User user  = userDAO.findById(dto.userId()).orElseThrow(() -> new APIException(404, "User with id " + dto.userId() + " not found"));

        ShoppingList list = ShoppingList.builder()
                .name(dto.name())
                .overallPrice(dto.overallPrice())
                .user(user)
                .build();

        ShoppingList saved = shoppingListDAO.save(list);

        ctx.status(201).json(ShoppingListDTO.fromEntity(saved));
    }
}
