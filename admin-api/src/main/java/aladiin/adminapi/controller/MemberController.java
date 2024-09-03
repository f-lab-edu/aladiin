package aladiin.adminapi.controller;

import aladiin.adminapi.model.dto.FindValidIssuedCouponsDTO;
import aladiin.adminapi.model.dto.FindValidIssuedCouponsResponse;
import aladiin.core.request.SignUpRequest;
import aladiin.adminapi.service.CouponService;
import aladiin.adminapi.service.MemberService;
import aladiin.core.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import aladiin.core.domain.entity.IssuedCoupon;
import aladiin.core.domain.entity.Member;
import aladiin.core.domain.enums.MemberStatus;
import aladiin.core.domain.enums.MemberType;
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
        FindValidIssuedCouponsResponse response =
                FindValidIssuedCouponsResponse.of(issuedCoupons.stream()
                        .map(ic -> FindValidIssuedCouponsDTO.of(ic.getCoupon().getCouponName()
                                , ic.getCoupon().getDiscount().getDiscountType().getType()
                                , ic.getCoupon().getDiscount().getDiscountValue()
                                , ic.getCoupon().getValidDateTime(), ic.getCreatedAt())).collect(Collectors.toList()));

        return ResponseEntity.ok(CommonResponse.ofSuccess(response));
    }
}