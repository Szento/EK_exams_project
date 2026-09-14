package app.dto.catalog;

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
public class CampaignDTO {

    @JsonProperty("contents")
    private double contents;

    @JsonProperty("contentsUnit")
    private String contentsUnit;

    @JsonProperty("displayText")
    private String displayText;

    @JsonProperty("fromDate")
    private Instant fromDate;

    @JsonProperty("groupDisplayText")
    private String groupDisplayText;

    @JsonProperty("price")
    private double price;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("showSavedMessage")
    private boolean showSavedMessage;

    @JsonProperty("text")
    private String text;

    @JsonProperty("toDate")
    private Instant toDate;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("unitPrice")
    private double unitPrice;
}
