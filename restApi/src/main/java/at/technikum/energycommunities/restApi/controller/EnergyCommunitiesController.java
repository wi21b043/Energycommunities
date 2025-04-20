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
     * 返回当前小时的百分比数据
     */
    @GetMapping("/current")
    public CurrentEnergyDto getCurrent() {
        return energyService.getCurrentEnergy();
    }

    /**
     * GET /energy/historical?start=...&end=...
     * 返回指定时间区间内的小时级历史数据
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

