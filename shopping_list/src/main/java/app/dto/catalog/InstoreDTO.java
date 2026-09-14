package app.dto.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstoreDTO {

    @JsonProperty("campaign")
    private CampaignDTO campaign;

    @JsonProperty("contents")
    private double contents;

    @JsonProperty("contentsUnit")
    private String contentsUnit;

    @JsonProperty("deposit")
    private DepositDTO deposit;

    @JsonProperty("description")
    private String description;

    @JsonProperty("ean")
    private String ean;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double price;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("unitPrice")
    private double unitPrice;
}
