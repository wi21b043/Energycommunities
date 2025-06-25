package at.technikum.energycommunities.percentageservice.repository;

import at.technikum.energycommunities.percentageservice.entity.CurrentPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface CurrentPercentageRepository extends JpaRepository<CurrentPercentage, LocalDateTime> {}
