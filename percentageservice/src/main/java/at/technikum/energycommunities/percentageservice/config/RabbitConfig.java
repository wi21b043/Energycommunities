package at.technikum.energycommunities.percentageservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /** 确保 energy.messages 队列存在 */
    @Bean
    public Queue energyMessagesQueue() {
        return new Queue("energy.messages", true);
    }

    /** 确保 energy.updates 队列存在 */
    @Bean
    public Queue energyUpdatesQueue() {
        return new Queue("energy.updates", true);
    }
}
