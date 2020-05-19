package fr.breuse.never_late.departures;

import feign.FeignException;
import fr.breuse.never_late.remote.sncf.client.SNCFClient;
import fr.breuse.never_late.remote.sncf.response.DepartureResponse;
import fr.breuse.never_late.remote.sncf.response.DisplayInformations;
import fr.breuse.never_late.remote.sncf.response.SNCFDepartureResponse;
import fr.breuse.never_late.remote.sncf.response.Schedules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeparturesServiceTest {
    private DeparturesService departuresService;

    @Mock
    private SNCFClient sncfClient;

    @BeforeEach
    void setUp() {
        departuresService = new DeparturesService(sncfClient);
    }

    @Test
    void should_fetch_and_return_departures() {
        given(sncfClient.getDepartures(anyString(), anyString())).willReturn(mockSNCFDepartureResponse());

        List<Departure> actualDepartures =
                departuresService.getDepartures("87381137", empty());

        assertEquals(actualDepartures, expectedDepartures());
    }

    @Test
    void should_fetch_and_return_departures_of_only_desired_line() {
        given(sncfClient.getDepartures(anyString(), anyString())).willReturn(mockSNCFDepartureResponse());

        List<Departure> actualDepartures =
                departuresService.getDepartures("87381137", of("L"));

        assertEquals(actualDepartures, expectedOneDeparture());
    }

    private SNCFDepartureResponse mockSNCFDepartureResponse() {
        return SNCFDepartureResponse.builder()
                .departures(asList(
                        departureResponse("L", "Nanterre", "20200518T171400"),
                        departureResponse("J", "Paris", "20200518T171700")))
                .build();
    }

    private DepartureResponse departureResponse(String code, String direction, String departureTime) {
        return DepartureResponse.builder()
                .displayInformations(DisplayInformations.builder()
                        .code(code)
                        .direction(direction)
                        .build())
                .schedules(Schedules.builder()
                        .departureTime(departureTime)
                        .build())
                .build();
    }

    private List<Departure> expectedDepartures() {
        return asList(departure("L", "Nanterre", LocalDateTime.parse("20200518T171400", DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"))),
                departure("J", "Paris", LocalDateTime.parse("20200518T171700", DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"))));
    }

    private List<Departure> expectedOneDeparture() {
        return singletonList(departure("L", "Nanterre", LocalDateTime.parse("20200518T171400", DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss"))));
    }

    private Departure departure(String line, String direction, LocalDateTime departureTime) {
        return Departure.builder()
                .line(line)
                .direction(direction)
                .departure(departureTime)
                .build();
    }
}
