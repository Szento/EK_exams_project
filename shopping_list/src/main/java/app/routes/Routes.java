package app.routes;

import app.controllers.FoodWasteController;
import app.controllers.ShoppingListController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Routes {
    private final FoodWasteController foodWasteController;
    private final ShoppingListController shoppingListController;

    public Routes(FoodWasteController foodWasteController, ShoppingListController shoppingListController) {
        this.foodWasteController = foodWasteController;
        this.shoppingListController = shoppingListController;
    }

    public EndpointGroup getRoutes() {
        return () -> {
            get("/food-waste/{zip}", foodWasteController::getByZip);
            get("/", ctx -> ctx.result("Hello"));

            path("shoppinglists", () -> {
                get(shoppingListController::getAll);
                get("{id}", shoppingListController::getById);
            });
        };

    }


}
