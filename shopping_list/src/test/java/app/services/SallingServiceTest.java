package app.services;

import app.api.APIReader;
import app.dto.foodwaste.StoreClearanceDTO;
import app.exceptions.APIException;
import app.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SallingServiceTest {

    private static SallingService service;

    @BeforeAll
    static void setUp() {
        String key = Utils.getPropertyValue("FWAPI_KEY", "config.properties");
        service = new SallingService(key, new APIReader());
    }

    @Test
    @DisplayName("Given a valid key and zip, when food waste is fetched, then stores are parsed")
    void getFoodWasteByZipReturnsParsedStores() {
        // Fails with a parse error if the DTOs don't match the JSON
        StoreClearanceDTO[] result = service.getFoodWasteByZip("8000");

        assertNotNull(result);
        assertTrue(result.length > 0, "Expected at least one store");
        assertNotNull(result[0].getStore());
    }

    @Test
    @DisplayName("Given a wrong key, when food waste is fetched, then APIException with auth code is thrown")
    void getFoodWasteByZipWithWrongKeyThrowsAuthError() {
        SallingService badService = new SallingService("wrong-key", new APIReader());

        APIException e = assertThrows(APIException.class, () -> badService.getFoodWasteByZip("8000"));

        assertTrue(e.getCode() == 401 || e.getCode() == 403,
                "Expected 401 or 403 but got " + e.getCode());
    }
}