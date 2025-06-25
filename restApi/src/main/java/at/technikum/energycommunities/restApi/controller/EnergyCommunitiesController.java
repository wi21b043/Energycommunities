package at.technikum.energycommunities.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnergyCommunitiesController {

    @GetMapping("/energy/current")
    public String getCurrent() {
        return "Aktuelle Prozentwerte: 100% verbraucht, 5% vom Netz";
    }

    @GetMapping("/energy/historical")
    public String getHistorical(@RequestParam String start, @RequestParam String end) {
        return "Historische Daten von " + start + " bis " + end;
    }
}