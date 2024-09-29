package aladiin.couponconsumer.kafka.producer;

import aladiin.core.request.EventJoinRequest;
import aladiin.couponconsumer.config.KafkaConfigurationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponRestockProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaConfigurationProperties kafkaConfigurationProperties;

    public void produce(EventJoinRequest eventJoinDTO) {
        log.info("produce [{}] into broker [{}]", eventJoinDTO, kafkaConfigurationProperties.getBootstrapServers());
        kafkaTemplate.send(kafkaConfigurationProperties.getTopic().get("coupon-restock"), String.valueOf(eventJoinDTO.getMemberId()), eventJoinDTO);
    }
}