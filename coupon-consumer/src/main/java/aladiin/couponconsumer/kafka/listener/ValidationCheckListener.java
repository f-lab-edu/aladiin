package aladiin.couponconsumer.kafka.listener;

import aladiin.core.domain.entity.EventJoinMember;
import aladiin.core.request.EventJoinRequest;
import aladiin.couponconsumer.error.DuplicateJoinException;
import aladiin.couponconsumer.kafka.producer.CouponRestockProducer;
import aladiin.couponconsumer.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationCheckListener {

    private final EventService eventService;
    private final CouponRestockProducer couponRestockProducer;

    @KafkaListener(topics = "${spring.kafka.topic.validation-check}", groupId = "${spring.kafka.topic.validation-check}")
    public void eventJoinListener(@Headers MessageHeaders messageHeaders, @Payload EventJoinRequest eventJoinRequest) throws DuplicateJoinException {
        // 메모리 중복 참여 사용자 검증
        EventJoinMember eventJoinMember = EventJoinMember.of(eventJoinRequest.getEventId(), eventJoinRequest.getEventDate(), eventJoinRequest.getMemberId());
        eventService.checkDuplicateJoin(eventJoinMember);
        couponRestockProducer.produce(eventJoinRequest);
    }

    @ExceptionHandler
    public void DuplicateJoinExceptionHandler(DuplicateJoinException e) {
        log.error("이벤트 중복 참여 요청, key:{}", e.getMessage());
    }
}
