package com.aladiin.domain.coupon.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponIssueDTO {

    private String memberId;
    private String eventId;
    private LocalDateTime issuedAt;

    public static CouponIssueDTO of(String memberId, String eventId) {
        return new CouponIssueDTO(memberId, eventId, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "CouponIssueDTO{" +
                "memberId=" + memberId +
                ", eventId=" + eventId +
                ", issuedAt=" + issuedAt +
                '}';
    }
}
