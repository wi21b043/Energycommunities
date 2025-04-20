package at.technikum.energycommunities.restApi.service;


import at.technikum.energycommunities.restApi.dto.CurrentEnergyDto;
import at.technikum.energycommunities.restApi.dto.HistoricalEnergyDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnergyService {

    /**
     * 返回当前小时的示例百分比数据
     */
    public CurrentEnergyDto getCurrentEnergy() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        // 示例：社区能源完全耗尽，电网占比 5.63%
        return new CurrentEnergyDto(now, 100.00, 5.63);
    }

    /**
     * 返回给定时间区间内每小时的示例历史数据
     */
    public List<HistoricalEnergyDto> getHistoricalEnergy(LocalDateTime start, LocalDateTime end) {
        List<HistoricalEnergyDto> list = new ArrayList<>();
        LocalDateTime cursor = start.truncatedTo(ChronoUnit.HOURS);
        LocalDateTime endHour = end.truncatedTo(ChronoUnit.HOURS);

        while (!cursor.isAfter(endHour)) {
            // 生成一些伪造数据
            double communityUsed = round(10 + Math.random() * 10);
            double gridUsed = round(1 + Math.random() * 5);
            list.add(new HistoricalEnergyDto(cursor, communityUsed, gridUsed));
            cursor = cursor.plusHours(1);
        }
        return list;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
