package at.technikum.energycommunities.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Spring Boot Startklasse – Modul: Energy Producer
 * Generiert alle 1–5 Sekunden eine PRODUCER-Nachricht (EnergyMessage) und
 * sendet diese an die RabbitMQ-Queue "energy.messages".
 */
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(RabbitTemplate rabbitTemplate) {
        return args -> {
            var rnd = ThreadLocalRandom.current();
            while (true) {
                // Zufällige Wartezeit: 1 bis 5 Sekunden
                Thread.sleep(1000 + rnd.nextInt(4000));
                // Zufälliger kWh-Wert (0.1 bis 0.5), auf drei Dezimalstellen gerundet
                double kwh = Math.round((0.1 + rnd.nextDouble() * 0.4) * 1000) / 1000.0;
                // Erzeuge EnergyMessage
                EnergyMessage msg = new EnergyMessage(
                        "PRODUCER",
                        "COMMUNITY",
                        kwh,
                        LocalDateTime.now()
                );
                // Sende an die RabbitMQ-Queue "energy.messages"
                rabbitTemplate.convertAndSend("energy.messages", msg);
                System.out.println("[Producer] Sent: " + msg);
            }
        };
    }
}
