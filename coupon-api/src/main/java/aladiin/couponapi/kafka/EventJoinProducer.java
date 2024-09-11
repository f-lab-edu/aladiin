package aladiin.couponapi.kafka;

import aladiin.couponapi.config.KafkaConfigurationProperties;
import aladiin.core.dto.EventJoinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventJoinProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaConfigurationProperties kafkaConfigurationProperties;

    public void produce(EventJoinDTO eventJoinDTO) {
        log.info("produce [{}] into broker [{}]", eventJoinDTO, kafkaConfigurationProperties.getBootstrapServers());
        kafkaTemplate.send(kafkaConfigurationProperties.getTopic(), String.valueOf(eventJoinDTO.getMemberId()), eventJoinDTO);
    }
}