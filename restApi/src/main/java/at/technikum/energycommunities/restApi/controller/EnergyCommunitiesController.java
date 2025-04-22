package at.technikum.energycommunities.restApi.controller;

import at.technikum.energycommunities.restApi.dto.CurrentEnergyDto;
import at.technikum.energycommunities.restApi.dto.HistoricalEnergyDto;
import at.technikum.energycommunities.restApi.service.EnergyService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/energy")
public class EnergyCommunitiesController {

    private final EnergyService energyService;

    public EnergyCommunitiesController(EnergyService energyService) {
        this.energyService = energyService;
    }

    /**
     * GET /energy/current
     * Gibt die Prozentsatz-Daten der aktuellen Stunde zurück.
     */
    @GetMapping("/current")
    public CurrentEnergyDto getCurrent() {
        return energyService.getCurrentEnergy();
    }

    /**
     * GET /energy/historical?start=...&end=...
     * Gibt die stündlichen historischen Daten für den angegebenen Zeitraum zurück.
     */
    @GetMapping("/historical")
    public List<HistoricalEnergyDto> getHistorical(
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,

            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end) {
        return energyService.getHistoricalEnergy(start, end);
    }
}

