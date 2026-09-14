package app.dto.foodwaste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
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
public class HoursDTO {

    @JsonProperty("date")
    private Instant date;

    @JsonProperty("type")
    private String type;

    @JsonProperty("open")
    private Instant open;

    @JsonProperty("close")
    private Instant close;

    @JsonProperty("closed")
    private boolean closed;

    @JsonProperty("customerFlow")
    private List<Integer> customerFlow;
}
