package at.technikum.energycommunities.percentageservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "current_percentage")
public class CurrentPercentage {
    @Id
    private LocalDateTime hour;
    private double communityDepleted;
    private double gridPortion;

    protected CurrentPercentage() {}

    public CurrentPercentage(LocalDateTime hour, double communityDepleted, double gridPortion) {
        this.hour = hour;
        this.communityDepleted = communityDepleted;
        this.gridPortion = gridPortion;
    }
    // getters/setters
    public LocalDateTime getHour() { return hour; }
    public double getCommunityDepleted() { return communityDepleted; }
    public void setCommunityDepleted(double communityDepleted) { this.communityDepleted = communityDepleted; }
    public double getGridPortion() { return gridPortion; }
    public void setGridPortion(double gridPortion) { this.gridPortion = gridPortion; }
}
