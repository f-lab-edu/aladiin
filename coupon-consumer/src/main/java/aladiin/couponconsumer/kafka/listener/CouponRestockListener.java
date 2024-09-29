package aladiin.couponconsumer.kafka.listener;

import aladiin.core.request.EventJoinRequest;
import aladiin.couponconsumer.kafka.producer.CouponIssueProducer;
import aladiin.couponconsumer.service.CouponStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponRestockListener {

    private final CouponStockService couponStockService;
    private final CouponIssueProducer couponIssueProducer;

    @KafkaListener(topics = "${spring.kafka.topic.coupon-restock}", groupId = "${spring.kafka.topic}")
    public void couponRestockListener(@Headers MessageHeaders messageHeaders, @Payload EventJoinRequest eventJoinRequest) {

        couponStockService.restock(eventJoinRequest.getEventId(), eventJoinRequest.getEventDate());
        couponIssueProducer.produce(eventJoinRequest);
    }
}
