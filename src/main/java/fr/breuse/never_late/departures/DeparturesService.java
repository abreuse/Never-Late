package fr.breuse.never_late.departures;

import fr.breuse.never_late.remote.sncf.client.SNCFClient;
import fr.breuse.never_late.remote.sncf.response.DepartureResponse;
import fr.breuse.never_late.remote.sncf.response.SNCFDepartureResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Service
public class DeparturesService {
    private final SNCFClient sncfClient;

    public DeparturesService(SNCFClient sncfClient) {
        this.sncfClient = sncfClient;
    }

    public List<Departure> getDepartures(String UICcode, Optional<String> line) {
        SNCFDepartureResponse response = sncfClient.getDepartures(UICcode, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss")));

        return response.getDepartures()
                .stream()
                .map(DepartureResponse::toDeparture)
                .filter(byLineIfPresent(line))
                .collect(toList());
    }

    private Predicate<Departure> byLineIfPresent(Optional<String> line) {
        return departure -> line.map(it -> departure.getLine().equals(it)).orElse(true);
    }
}
