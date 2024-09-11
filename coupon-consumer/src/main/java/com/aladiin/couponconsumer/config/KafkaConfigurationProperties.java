package com.aladiin.couponconsumer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter @Setter
@Component
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaConfigurationProperties {
    private String topic;
    private List<String> bootstrapServers;
}


