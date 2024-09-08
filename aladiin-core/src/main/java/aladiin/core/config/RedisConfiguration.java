package aladiin.core.config;

import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

    private final RedisConfigurationProperties redisConfigurationProperties;

    @Bean
    public RedissonConnectionFactory redisConnectionFactory(RedissonClient redissonClient) {
        return new RedissonConnectionFactory(redissonClient);
    }

    @Bean
    public RedissonClient RedissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .setNodeAddresses(redisConfigurationProperties.getHost());
        return Redisson.create(config);
    }


}
