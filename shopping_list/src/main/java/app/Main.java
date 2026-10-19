package app;

import app.controllers.ShoppingListController;
import app.dao.ShoppingListDAOImpl;
import app.routes.Routes;
import app.api.APIReader;
import app.config.ApplicationConfig;
import app.controllers.FoodWasteController;
import app.services.SallingService;
import app.utils.Utils;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        /* using ternery operator can we ask if the server has been Deployed and then access
        the api via System.getenv().
        otherwise we access the api by the local value, Utils.getPropertyValue(),
        which is what we do. as this is a test api from sallinggroup.
         */
        String FWAPIKEY = System.getenv("DEPLOYED") != null
                ? System.getenv("FWAPI_KEY")
                : Utils.getPropertyValue("FWAPI_KEY", "config.properties");

        String CAPIKEY = System.getenv("DEPLOYED") != null
                ? System.getenv("CAPI_KEY")
                : Utils.getPropertyValue("CAPI_KEY", "config.properties");

        APIReader apiReader = new APIReader();
        SallingService foodWasteService = new SallingService(FWAPIKEY, apiReader);
        FoodWasteController foodWasteController = new FoodWasteController(foodWasteService);
        ShoppingListController shoppingListController = new ShoppingListController(new ShoppingListDAOImpl());
        Routes routes = new Routes(foodWasteController, shoppingListController);

        Javalin app = ApplicationConfig.createServer(routes.getRoutes());
        app.start(8000);
    }
}