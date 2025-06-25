package at.technikum.energycommunities.usageservice.entity;

import java.time.LocalDateTime;

public class UsageEntry {
    private String type;
    private String association;
    private double kwh;
    private LocalDateTime datetime;

    // Constructor, Getter, Setter, toString()
    public UsageEntry(String type, String association, double kwh, LocalDateTime datetime) {
        this.type = type;
        this.association = association;
        this.kwh = kwh;
        this.datetime = datetime;
    }

    // ... Standard Getter und Setter

    @Override
    public String toString() {
        return "UsageEntry{" +
                "type='" + type + '\'' +
                ", association='" + association + '\'' +
                ", kwh=" + kwh +
                ", datetime=" + datetime +
                '}';
    }
}
