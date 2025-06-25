package at.technikum.energycommunities.percentageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "at.technikum.energycommunities.percentageservice",
                "at.technikum.energycommunities.usageservice"
        }
)
@EnableJpaRepositories(basePackages = {
        "at.technikum.energycommunities.percentageservice.repository",
        "at.technikum.energycommunities.usageservice.repository"
})
@EntityScan(basePackages = {
        "at.technikum.energycommunities.percentagesevice.entity",
        "at.technikum.energycommunities.usageservice.entity"
})
public class PercentageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PercentageServiceApplication.class, args);
    }
}
