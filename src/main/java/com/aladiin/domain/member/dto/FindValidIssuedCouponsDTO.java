package com.aladiin.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName = "of")
public class FindValidIssuedCouponsDTO {

    private String couponName;
    private String discountType;
    private int discountValue;
    private LocalDateTime validDateTime;
    private LocalDateTime createdAt;
}
