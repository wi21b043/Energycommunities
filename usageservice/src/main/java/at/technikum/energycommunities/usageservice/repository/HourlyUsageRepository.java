package at.technikum.energycommunities.usageservice.repository;

import at.technikum.energycommunities.usageservice.entity.HourlyUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository für CRUD-Operationen auf HourlyUsage.
 */
public interface HourlyUsageRepository
        extends JpaRepository<HourlyUsage, LocalDateTime> {

    /**
     * Finde alle HourlyUsage Einträge im Zeitraum von start bis end (inklusive).
     */
    List<HourlyUsage> findAllByHourBetween(LocalDateTime start, LocalDateTime end);
}
