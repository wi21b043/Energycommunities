package at.technikum.energycommunities.usageservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue energyMessagesQueue() {
        return new Queue("energy.messages", true);
    }

    @Bean
    public Queue energyUpdatesQueue() {
        return new Queue("energy.updates", true);
    }
}


