package app.dto.foodwaste;

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
public class ProductDTO {

    @JsonProperty("categories")
    private CategoriesDTO categories;

    @JsonProperty("description")
    private String description;

    @JsonProperty("ean")
    private String ean;

    @JsonProperty("image")
    private String image;
}
