package app.dto.foodwaste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDate date;

    @JsonProperty("type")
    private String type;

    /*Salling uses 24:00 for midnight which isn't valid in a LocalDateTime.
    My application only shows open hours, without calculating anything, therefore the string is a valid option
     */
    @JsonProperty("open")
    private String open;

    @JsonProperty("close")
    private String close;

    @JsonProperty("closed")
    private boolean closed;

    @JsonProperty("customerFlow")
    private List<Integer> customerFlow;
}
