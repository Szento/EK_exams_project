package app.controllers;

import app.dto.foodwaste.StoreClearanceDTO;
import app.services.SallingService;
import io.javalin.http.Context;

public class FoodWasteController {
    private final SallingService sallingService;

    public  FoodWasteController(SallingService sallingService){
        this.sallingService = sallingService;
    }

    public void getByZip(Context ctx){
        String zip = ctx.pathParamAsClass("zip", String.class)
                .check(value -> value.matches("\\d{4}"), "Zip code must be 4 digits")
                .get();
        StoreClearanceDTO[] stores = sallingService.getFoodWasteByZip(zip);

        ctx.header("Cache-Control", "public, max-age=120");
        ctx.json(stores);

    }
}
