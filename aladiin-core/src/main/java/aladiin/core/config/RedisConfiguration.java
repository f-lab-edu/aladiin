package aladiin.core.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RedisConfiguration {


    @Value("${spring.redis.host}")
    private List<String> host;

    @Bean
    public RedissonConnectionFactory redisConnectionFactory(RedissonClient redissonClient) {
        return new RedissonConnectionFactory(redissonClient);
    }

    @Bean
    public RedissonClient RedissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .setNodeAddresses(host);
        return Redisson.create(config);
    }


}
