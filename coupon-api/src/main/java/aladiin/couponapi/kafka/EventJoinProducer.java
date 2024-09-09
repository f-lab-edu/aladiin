package aladiin.couponapi.kafka;

import aladiin.couponapi.model.dto.EventJoinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventJoinProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;
    @Value("${spring.kafka.bootstrap-servers}")
    private String broker;


    public void produce(EventJoinDTO eventJoinDTO) {
        log.info("produce [{}] into broker [{}]", eventJoinDTO, broker);
        kafkaTemplate.send(topic, eventJoinDTO);
    }
}