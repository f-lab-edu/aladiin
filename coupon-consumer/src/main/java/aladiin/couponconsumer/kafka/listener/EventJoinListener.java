package aladiin.couponconsumer.kafka.listener;

import aladiin.core.domain.entity.EventJoinMember;
import aladiin.core.request.EventJoinRequest;
import aladiin.couponconsumer.service.EventService;
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
public class EventJoinListener {

    private final EventService eventService;

    @KafkaListener(topics = "${spring.kafka.topic.event-join}", groupId = "${spring.kafka.event-join}")
    public void eventJoinListener(@Headers MessageHeaders messageHeaders, @Payload EventJoinRequest eventJoinRequest) {
        EventJoinMember eventJoinMember = EventJoinMember.of(eventJoinRequest.getEventId(), eventJoinRequest.getEventDate(), eventJoinRequest.getMemberId());
        eventService.join(eventJoinMember);
    }
}