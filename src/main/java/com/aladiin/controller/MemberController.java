package com.aladiin.controller;

import com.aladiin.model.entity.IssuedCoupon;
import com.aladiin.model.entity.Member;
import com.aladiin.model.dto.FindValidIssuedCouponsDTO;
import com.aladiin.model.dto.FindValidIssuedCouponsResponse;
import com.aladiin.model.dto.SignUpRequest;
import com.aladiin.model.enums.MemberStatus;
import com.aladiin.model.enums.MemberType;
import com.aladiin.common.response.CommonResponse;
import com.aladiin.service.CouponService;
import com.aladiin.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final CouponService couponService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signUp(@RequestBody SignUpRequest request) {

        Member member = Member.builder()
                .memberName(request.getMemberName())
                .memberType(MemberType.GENERAL)
                .memberStatus(MemberStatus.ACTIVE)
                .build();

        memberService.saveMember(member);

        return ResponseEntity.ok(CommonResponse.ofSuccess());
    }

    @GetMapping("{memberId}/coupons")
    public ResponseEntity<CommonResponse> findValidIssuedCoupons(@PathVariable Long memberId) {

        List<IssuedCoupon> issuedCoupons = couponService.findValidIssuedCoupons(memberId);
        FindValidIssuedCouponsResponse response = FindValidIssuedCouponsResponse.of(issuedCoupons.stream().map(ic -> FindValidIssuedCouponsDTO.of(ic.getCoupon().getCouponName()
                , ic.getCoupon().getDiscount().getDiscountType().getType()
                , ic.getCoupon().getDiscount().getDiscountValue()
                , ic.getCoupon().getValidDateTime(), ic.getCreatedAt())).collect(Collectors.toList()));

        return ResponseEntity.ok(CommonResponse.ofSuccess(response));
    }
}
