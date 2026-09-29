package app;

import app.api.APIReader;
import app.utils.Utils;

public class Main {
    public static void main(String[] args) {
        String FWAPIKEY = System.getenv("DEPLOYED") != null
                ? System.getenv("FWAPI_KEY")
                : Utils.getPropertyValue("FWAPI_KEY", "config.properties");

        String CAPIKEY = System.getenv("DEPLOYED") != null
                ? System.getenv("CAPI_KEY")
                : Utils.getPropertyValue("CAPI_KEY", "config.properties");

        APIReader apiReader = new APIReader();

    }
}