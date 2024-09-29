package aladiin.couponconsumer.kafka.listener;

import aladiin.core.domain.entity.Event;
import aladiin.core.domain.entity.IssuedCoupon;
import aladiin.core.domain.entity.Member;
import aladiin.core.request.EventJoinRequest;
import aladiin.couponconsumer.kafka.producer.EventJoinProducer;
import aladiin.couponconsumer.service.CouponService;
import aladiin.couponconsumer.service.CouponStockService;
import aladiin.couponconsumer.service.EventService;
import aladiin.couponconsumer.service.MemberService;
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
public class CouponIssueListener {


    private final EventService eventService;
    private final MemberService memberService;
    private final CouponService couponService;
    private final CouponStockService couponStockService;
    private final EventJoinProducer eventJoinProducer;

    @KafkaListener(topics = "${spring.kafka.topic.coupon-issue}", groupId = "${spring.kafka.topic.coupon-issue}")
    public void eventJoinListener(@Headers MessageHeaders messageHeaders, @Payload EventJoinRequest eventJoinRequest) {
        Member member = memberService.findById(eventJoinRequest.getMemberId());
        Event event = eventService.findById(eventJoinRequest.getEventId());
        IssuedCoupon issuedCoupon = IssuedCoupon.of(member, event);

        //  쿠폰 발급
        //  ㄴ 메모리 쿠폰 재고 감소
        //  ㄴ RDB 쿠폰 발급 처리
        couponStockService.decrease(eventJoinRequest.getEventId(), eventJoinRequest.getEventDate());
        couponService.issue(issuedCoupon);

        eventJoinProducer.produce(eventJoinRequest);
    }
}
