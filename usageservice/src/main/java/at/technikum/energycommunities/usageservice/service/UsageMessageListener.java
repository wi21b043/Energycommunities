package at.technikum.energycommunities.usageservice.service;

import at.technikum.energycommunities.usageservice.dto.EnergyMessage;
import at.technikum.energycommunities.usageservice.entity.UsageEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class UsageMessageListener {

    // Dummy-"Tabelle" im Speicher
    private final List<UsageEntry> usageEntries = new CopyOnWriteArrayList<>();
    private final RabbitTemplate rabbitTemplate;

    public UsageMessageListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "energy.messages")
    public void receiveMessage(EnergyMessage message) {
        System.out.println("[UsageService] Received: " + message);
        usageEntries.add(new UsageEntry(message.type(), message.kwh(), message.datetime()));

        // Hier würdest du normalerweise die Tabelle aktualisieren.
        // Sende jetzt ein Update-Message an "usage.updates" (optional, je nach Architektur).
        rabbitTemplate.convertAndSend("usage.updates", message); // weiterleiten für den PercentageService
        System.out.println("[UsageService] Update sent to usage.updates: " + message);
    }

    // Optional: Getter zum Auslesen (z.B. für REST oder Debug)
    public List<UsageEntry> getUsageEntries() {
        return usageEntries;
    }
}
