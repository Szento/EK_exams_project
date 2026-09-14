package app.dto.foodwaste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreClearanceDTO {

    @JsonProperty("clearances")
    private List<ClearanceDTO> clearances;

    @JsonProperty("store")
    private StoreDTO store;
}