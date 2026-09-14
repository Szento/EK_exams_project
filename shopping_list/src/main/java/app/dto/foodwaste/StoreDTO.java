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
public class StoreDTO {

    @JsonProperty("address")
    private AddressDTO address;

    @JsonProperty("brand")
    private String brand;

    // [longitude, latitude], as given by the API
    @JsonProperty("coordinates")
    private List<Double> coordinates;

    @JsonProperty("hours")
    private List<HoursDTO> hours;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("distance_km")
    private double distanceKm;

    @JsonProperty("type")
    private String type;
}
