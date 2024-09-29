package aladiin.couponapi.controller;

import aladiin.core.response.CommonResponse;
import aladiin.couponapi.kafka.EventJoinProducer;
import aladiin.core.dto.EventJoinDTO;
import aladiin.core.request.EventJoinRequest;
import aladiin.couponapi.model.dto.JoinStatusResponse;
import aladiin.couponapi.model.enums.EventJoinStatus;
import aladiin.couponapi.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/events")
public class EventController {

    private final EventService eventService;
    private final EventJoinProducer eventJoinProducer;

    @GetMapping("/{eventId}/status")
    private ResponseEntity<CommonResponse> getJoinStatus(@PathVariable Long eventId,
                                                         @RequestParam(name = "eventdate") String eventDate,
                                                         @RequestParam(name = "memberid") Long memberId) {
        EventJoinStatus eventJoinStatus = eventService.getJoinStatus(eventId, eventDate, memberId);
        return ResponseEntity.ok(CommonResponse.ofSuccess(JoinStatusResponse.of(eventJoinStatus)));
    }

    @PostMapping("/join")
    public ResponseEntity<CommonResponse> join(@Valid @RequestBody EventJoinRequest request){
        eventJoinProducer.produce(EventJoinDTO.of(request.getMemberId(), request.getEventId(), request.getEventDate()));
        return ResponseEntity.ok(CommonResponse.ofSuccess());
    }
}

