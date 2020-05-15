package fr.breuse.never_late.departures;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Departure {
    private String direction;
    private String line;
    private LocalDateTime departure;
}
