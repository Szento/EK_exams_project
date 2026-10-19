package app.routes;

import app.controllers.FoodWasteController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Routes {
    private final FoodWasteController foodWasteController;

    public Routes(FoodWasteController foodWasteController) {
        this.foodWasteController = foodWasteController;
    }

    public EndpointGroup getRoutes() {
        return () -> {
            get("/food-waste/{zip}", foodWasteController::getByZip);
            get("/", ctx -> ctx.result("Hello"));
        };
    }
}
