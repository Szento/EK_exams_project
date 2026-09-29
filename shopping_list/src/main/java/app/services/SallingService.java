package app.services;

import app.api.APIReader;
import app.dto.catalog.ItemDTO;
import app.dto.foodwaste.StoreClearanceDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SallingService {
    
    private static final Logger logger = LoggerFactory.getLogger(SallingService.class);
    private static final String BASE_URL = "https://api.sallinggroup.com";
    private final String apiKey;
    private final APIReader apiReader;

    public SallingService(String apiKey, APIReader apiReader) {
        this.apiKey = apiKey;
        this.apiReader = apiReader;
    }

    public Object getFoodWasteByZip(String zip){
        String url = BASE_URL + "/v1/food-waste/{storeId}" + zip;
        logger.debug("Fetching food waste for zip {}", zip);
        return apiReader.getWithJacksonGeneric(url, StoreClearanceDTO[].class, apiKey);
    }
    public ItemDTO getItemByEAN(String ean){
        String url = BASE_URL + "/v2/products/{ean}" + ean;
        logger.debug("Fetching product for EAN {}", ean);
        return apiReader.getWithJacksonGeneric(url, ItemDTO.class, apiKey);
    }
}