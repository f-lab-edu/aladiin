package com.aladiin.infra.kafka.producer;

import com.aladiin.domain.coupon.dto.CouponIssueDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CouponIssueProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;
    @Value("${spring.kafka.bootstrap-servers}")
    private String broker;


    public void produce(CouponIssueDTO couponIssueDTO) {
        log.info("produce [{}] into broker [{}]", couponIssueDTO, broker);
        kafkaTemplate.send(topic, couponIssueDTO);
    }
}
