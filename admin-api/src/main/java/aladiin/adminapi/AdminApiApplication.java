package aladiin.adminapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties
@EntityScan(basePackages = {"aladiin.core", "com.aladiin.adminapi"})
@EnableJpaRepositories(basePackages = {"aladiin.core", "aladiin.adminapi"})
@SpringBootApplication(scanBasePackages = {"aladiin.core", "aladiin.adminapi"})
public class AdminApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApiApplication.class, args);
    }
}
