package fr.breuse.never_late.departures;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departures")
public class DeparturesController {

    private final DeparturesService departuresService;

    public DeparturesController(DeparturesService departuresService) {
        this.departuresService = departuresService;
    }

    @GetMapping("/{UICcode}")
    public ResponseEntity<List<Departure>> getDepartures(@PathVariable("UICcode") String UICcode,
                                                         @RequestParam("line") Optional<String> line) {
        List<Departure> departures = departuresService.getDepartures(UICcode, line);

        return ResponseEntity.ok(departures);
    }
}
