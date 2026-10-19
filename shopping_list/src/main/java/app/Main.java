package app;

import app.api.APIReader;
import app.config.ApplicationConfig;
import app.dto.foodwaste.StoreClearanceDTO;
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

        Javalin app = ApplicationConfig.createServer();
        app.get("/", ctx -> ctx.result("Hello"));

        app.get("/food-waste/{zip}", ctx -> {
            String zip = ctx.pathParamAsClass("zip", String.class)
                    .check(value -> value.matches("\\d{4}"), "Zip code must be 4 digits")
                    .get();
            StoreClearanceDTO[] stores = foodWasteService.getFoodWasteByZip(zip);

            ctx.header("Cache-Control", "public, max-age=120");
            ctx.json(stores);
        });
        app.start(8000);
    }
}