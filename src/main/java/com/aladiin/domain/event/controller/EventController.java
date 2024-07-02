package com.aladiin.domain.event.controller;

import com.aladiin.domain.coupon.domain.entity.Coupon;
import com.aladiin.domain.coupon.dto.CouponIssueDTO;
import com.aladiin.domain.coupon.exception.NoSuchCouponExistException;
import com.aladiin.domain.coupon.service.CouponService;
import com.aladiin.domain.event.dto.EventJoinDTO;
import com.aladiin.domain.event.dto.EventJoinRequest;
import com.aladiin.domain.event.dto.EventRegisterRequest;
import com.aladiin.domain.event.exception.EventAlreadyJoinedException;
import com.aladiin.domain.event.exception.EventParticipantExceedException;
import com.aladiin.domain.event.exception.NoSuchEventExistException;
import com.aladiin.domain.event.service.EventService;
import com.aladiin.domain.member.exception.NoSuchMemberExistException;
import com.aladiin.global.common.response.CommonResponse;
import com.aladiin.infra.kafka.producer.CouponIssueProducer;
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
