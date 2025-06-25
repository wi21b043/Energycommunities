package at.technikum.energycommunities.percentageservice.service;

import at.technikum.energycommunities.usageservice.entity.HourlyUsage;
import at.technikum.energycommunities.usageservice.repository.HourlyUsageRepository;
import at.technikum.energycommunities.percentageservice.entity.CurrentPercentage;
import at.technikum.energycommunities.percentageservice.repository.CurrentPercentageRepository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class PercentageListener {

    private final HourlyUsageRepository usageRepo;
    private final CurrentPercentageRepository pctRepo;

    public PercentageListener(HourlyUsageRepository usageRepo,
                              CurrentPercentageRepository pctRepo) {
        this.usageRepo = usageRepo;
        this.pctRepo   = pctRepo;
    }

    @RabbitListener(queues = "energy.updates")
    public void onHourUpdate(LocalDateTime hour) {
        LocalDateTime h = hour.truncatedTo(ChronoUnit.HOURS);
            usageRepo.findById(h).ifPresent((HourlyUsage u) -> {
            double total    = u.getCommunityUsed() + u.getGridUsed();
            double depleted = total == 0 ? 0 : u.getCommunityUsed() / total * 100;
            double gridPct  = total == 0 ? 0 : u.getGridUsed() / total * 100;
            CurrentPercentage cp = new CurrentPercentage(h, depleted, gridPct);
            pctRepo.save(cp);
        });
    }
}
