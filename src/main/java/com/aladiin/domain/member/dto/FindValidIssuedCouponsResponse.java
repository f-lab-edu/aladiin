package com.aladiin.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class FindValidIssuedCouponsResponse {

    private List<FindValidIssuedCouponsDTO> FindValidIssuedCouponsDTOs;
}
