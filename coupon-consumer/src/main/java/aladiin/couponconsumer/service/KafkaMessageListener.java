package aladiin.couponconsumer.service;

import aladiin.core.domain.entity.EventJoinMember;
import aladiin.core.request.EventJoinRequest;
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
public class KafkaMessageListener {

    private final EventService eventService;
    private final CouponService couponService;

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.topic}")
    public void eventJoinListener(@Headers MessageHeaders messageHeaders, @Payload EventJoinRequest eventJoinRequest) {
        // 1. 메모리 중복 참여 사용자 검증
        EventJoinMember eventJoinMember = EventJoinMember.of(eventJoinRequest.getEventId(), eventJoinRequest.getEventDate(), eventJoinRequest.getMemberId());
        eventService.checkDuplicateJoin(eventJoinMember);

        // 2. 잔여 쿠폰 확인
        //  ㄴ 쿠폰 존재할 경우 skip
        //  ㄴ 쿠폰 미존재할 경우 redis 에서 쿠폰 충전
        couponService.restock();

        // 3. 쿠폰 발급
        //  ㄴ 메모리 쿠폰 재고 감소
        //  ㄴ RDB 쿠폰 발급 처리
        couponService.issue(eventJoinRequest.getMemberId());

        // 4. 이벤트 참여 처리
        //  ㄴ 메모리 이벤트 처리
        //  ㄴ redis 이벤트 처리
        eventService.join(eventJoinRequest.getMemberId(), eventJoinRequest.getEventId(), eventJoinRequest.getEventDate());
    }
}
