package at.technikum.energycommunities.usageservice.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record EnergyMessage(
        String type,
        String association,
        double kwh,
        LocalDateTime datetime
) implements Serializable {}
