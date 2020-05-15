package fr.breuse.never_late.remote.sncf.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.breuse.never_late.departures.Departure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartureResponse {

    @JsonProperty("display_informations")
    private DisplayInformations displayInformations;

    @JsonProperty("stop_date_time")
    private Schedules schedules;

    public Departure toDeparture() {
        return Departure.builder()
                .direction(this.displayInformations.getDirection())
                .line(this.displayInformations.getCode())
                .departure(toLocalDateTime(this.schedules.getDepartureTime()))
                .build();
    }

    private LocalDateTime toLocalDateTime(String departureTime) {
        return LocalDateTime.parse(departureTime, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"));
    }
}
