package aladiin.adminapi.controller;

import aladiin.adminapi.model.dto.EventRegisterRequest;
import aladiin.adminapi.service.CouponService;
import aladiin.adminapi.service.EventService;
import aladiin.adminapi.exception.NoSuchCouponExistException;
import aladiin.core.common.response.CommonResponse;
import aladiin.core.domain.entity.Event;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import aladiin.core.domain.entity.Coupon;
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

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> register(@Valid @RequestBody EventRegisterRequest request) throws NoSuchCouponExistException {
        Coupon coupon = couponService.findCouponById(request.getCouponId());
        Event event = request.toEntity(coupon);
        eventService.saveEvent(event);
        return ResponseEntity.ok(CommonResponse.ofSuccess());
    }
}