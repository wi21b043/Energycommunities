package at.technikum.energycommunities.restApi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class HistoricalEnergyDto {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime hour;
    private double communityUsed;
    private double gridUsed;

    public HistoricalEnergyDto() { }

    public HistoricalEnergyDto(LocalDateTime hour, double communityUsed, double gridUsed) {
        this.hour = hour;
        this.communityUsed = communityUsed;
        this.gridUsed = gridUsed;
    }

    public LocalDateTime getHour() {
        return hour;
    }

    public void setHour(LocalDateTime hour) {
        this.hour = hour;
    }

    public double getCommunityUsed() {
        return communityUsed;
    }

    public void setCommunityUsed(double communityUsed) {
        this.communityUsed = communityUsed;
    }

    public double getGridUsed() {
        return gridUsed;
    }

    public void setGridUsed(double gridUsed) {
        this.gridUsed = gridUsed;
    }
}

