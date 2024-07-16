package com.aladiin.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EventJoinDTO {

    private Long memberId;
    private Long eventId;
    private int couponQuantity;

    public static EventJoinDTO of(Long memberId, Long eventId, int couponQuantity) {
        return new EventJoinDTO(memberId, eventId, couponQuantity);
    }
}
