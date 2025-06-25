package at.technikum.energycommunities.user;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Spring Boot Startklasse – Community Energy User Modul
 * Sendet alle 1–5 Sekunden eine USER-Nachricht an die RabbitMQ Queue "energy.messages".
 */
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(RabbitTemplate rabbitTemplate) {
        return args -> {
            var rnd = ThreadLocalRandom.current();
            while (true) {
                // Warte zufällig zwischen 1 und 5 Sekunden
                Thread.sleep(1000 + rnd.nextInt(4000));
                // Generiere einen kWh-Wert zwischen 0.05 und 0.2 (drei Nachkommastellen)
                double kwh = Math.round((0.05 + rnd.nextDouble() * 0.15) * 1000) / 1000.0;
                // Erstelle die Nachricht
                EnergyMessage msg = new EnergyMessage(
                        "USER",
                        "COMMUNITY",
                        kwh,
                        LocalDateTime.now()
                );
                // Sende an RabbitMQ Queue "energy.messages"
                rabbitTemplate.convertAndSend("energy.messages", msg);
                System.out.println("[User] Sent: " + msg);
            }
        };
    }
}
