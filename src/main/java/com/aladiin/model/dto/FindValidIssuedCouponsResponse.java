package com.aladiin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class FindValidIssuedCouponsResponse {

    private List<FindValidIssuedCouponsDTO> FindValidIssuedCouponsDTOs;
}
