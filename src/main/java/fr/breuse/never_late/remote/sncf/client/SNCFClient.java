package fr.breuse.never_late.remote.sncf.client;

import fr.breuse.never_late.remote.sncf.response.SNCFDepartureResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SNCFClient",
        url = "https://api.sncf.com/v1/coverage/sncf/",
        configuration = SNCFClientConfiguration.class)
public interface SNCFClient {

    @RequestMapping(method = RequestMethod.GET,
            path = "/stop_areas/stop_area:OCE:SA:{UICcode}/departures")
    SNCFDepartureResponse getDepartures(@PathVariable("UICcode") String UICcode, @RequestParam("datetime") String datetime);
}
