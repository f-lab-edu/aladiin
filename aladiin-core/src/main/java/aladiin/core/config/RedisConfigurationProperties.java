package aladiin.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter @Setter
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigurationProperties {

    private List<String> host;
}
