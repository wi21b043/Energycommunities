package at.technikum.energycommunities.usageservice.service;

import at.technikum.energycommunities.usageservice.dto.EnergyMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * Hört auf die energy.messages-Queue, verarbeitet alle Nachrichten,
 * und merkt sich die Summen nur im Speicher.
 */
@Service
public class UsageListener {

    // Stundensummen nur im Speicher (keine echte DB)
    private final Map<LocalDateTime, Double> produced = new HashMap<>();
    private final Map<LocalDateTime, Double> used = new HashMap<>();

    private final RabbitTemplate rabbit;

    public UsageListener(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @RabbitListener(queues = "energy.messages")
    public void handle(EnergyMessage msg) {
        LocalDateTime hour = msg.datetime().truncatedTo(ChronoUnit.HOURS);

        if ("PRODUCER".equals(msg.type())) {
            produced.put(hour, produced.getOrDefault(hour, 0.0) + msg.kwh());
            System.out.println("[UsageService] Produziert: " + msg.kwh() + " kWh in Stunde " + hour);
        } else if ("USER".equals(msg.type())) {
            used.put(hour, used.getOrDefault(hour, 0.0) + msg.kwh());
            System.out.println("[UsageService] Verbraucht: " + msg.kwh() + " kWh in Stunde " + hour);
        }

        // Sende nach jeder Änderung ein "Update" auf energy.updates (hier nur die Stunde als Beispiel)
        rabbit.convertAndSend("energy.updates", hour);
    }
}
