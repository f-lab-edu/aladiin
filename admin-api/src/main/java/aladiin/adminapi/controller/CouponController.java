package aladiin.adminapi.controller;

import aladiin.adminapi.exception.InvalidDiscountException;
import aladiin.adminapi.model.dto.CouponRegisterRequest;
import aladiin.adminapi.service.CouponService;
import aladiin.core.common.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/coupons")
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> register(@RequestBody @Valid CouponRegisterRequest request) throws InvalidDiscountException {

        couponService.saveCoupon(request.toEntity());
        return ResponseEntity.ok(CommonResponse.ofSuccess());
    }

}
