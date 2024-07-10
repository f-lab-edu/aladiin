package com.aladiin.model.dto;

import lombok.Getter;

@Getter
public class EventJoinRequest {

    private Long memberId;
    private Long eventId;
    private int couponQuantity;

}
