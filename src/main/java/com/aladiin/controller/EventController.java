package com.aladiin.controller;

import com.aladiin.model.entity.Coupon;
import com.aladiin.model.dto.CouponIssueDTO;
import com.aladiin.exception.NoSuchCouponExistException;
import com.aladiin.model.dto.EventJoinDTO;
import com.aladiin.model.dto.EventJoinRequest;
import com.aladiin.model.dto.EventRegisterRequest;
import com.aladiin.exception.EventAlreadyJoinedException;
import com.aladiin.exception.EventParticipantExceedException;
import com.aladiin.exception.NoSuchEventExistException;
import com.aladiin.exception.NoSuchMemberExistException;
import com.aladiin.common.response.CommonResponse;
import com.aladiin.infra.kafka.producer.CouponIssueProducer;
import com.aladiin.service.CouponService;
import com.aladiin.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final CouponService couponService;

    private final CouponIssueProducer couponIssueProducer;

    @PostMapping("/register")

    public ResponseEntity<CommonResponse> register(@Valid @RequestBody EventRegisterRequest request) throws NoSuchCouponExistException {
        Coupon coupon = couponService.findCouponById(request.getCouponId());
        eventService.saveEvent(request.toEntity(coupon));
        return ResponseEntity.ok(CommonResponse.ofSuccess());
    }

    @PostMapping("/join")
    public ResponseEntity<CommonResponse> join(@Valid @RequestBody EventJoinRequest request)
            throws EventParticipantExceedException, NoSuchEventExistException, EventAlreadyJoinedException, NoSuchMemberExistException {
        EventJoinDTO eventJoinDTO = EventJoinDTO.of(request.getMemberId(), request.getEventId(), request.getCouponQuantity());
        eventService.join(eventJoinDTO);
//        eventService.issueCoupon(eventJoinDTO);
        couponIssueProducer.produce(CouponIssueDTO.of(String.valueOf(request.getMemberId()), String.valueOf(request.getEventId())));

        return ResponseEntity.ok(CommonResponse.ofSuccess());
    }

}
