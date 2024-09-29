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
public class CouponIssueProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaConfigurationProperties kafkaConfigurationProperties;

    public void produce(EventJoinRequest eventJoinRequest) {
        log.info("produce [{}] into broker [{}]", eventJoinRequest, kafkaConfigurationProperties.getBootstrapServers());
        kafkaTemplate.send(kafkaConfigurationProperties.getTopic().get("coupon-issue"), String.valueOf(eventJoinRequest.getMemberId()), eventJoinRequest);
    }
}