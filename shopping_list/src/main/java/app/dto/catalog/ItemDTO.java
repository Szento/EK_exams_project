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
public class ItemDTO {

    @JsonProperty("instore")
    private InstoreDTO instore;

    @JsonProperty("webshop")
    private WebshopDTO webshop;
}
