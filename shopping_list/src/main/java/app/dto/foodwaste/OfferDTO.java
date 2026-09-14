package app.dto.foodwaste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferDTO {

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("discount")
    private double discount;

    @JsonProperty("ean")
    private String ean;

    @JsonProperty("endTime")
    private Instant endTime;

    @JsonProperty("lastUpdate")
    private Instant lastUpdate;

    @JsonProperty("newPrice")
    private double newPrice;

    @JsonProperty("originalPrice")
    private double originalPrice;

    @JsonProperty("percentDiscount")
    private double percentDiscount;

    @JsonProperty("startTime")
    private Instant startTime;

    @JsonProperty("stock")
    private int stock;

    @JsonProperty("stockUnit")
    private String stockUnit;
}
