package app.dto.catalog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * NOTE: In the sample response, "webshop" was null, so its actual field
 * shape is unknown. This class is a placeholder — fill in fields once
 * you have a non-null example from the API. It likely mirrors InstoreDTO
 * (price, unit, campaign, etc.) but that is not confirmed yet.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebshopDTO {

}
