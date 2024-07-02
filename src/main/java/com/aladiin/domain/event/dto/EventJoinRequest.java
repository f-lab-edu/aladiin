package com.aladiin.domain.event.dto;

import com.aladiin.domain.event.domain.entity.Event;
import com.aladiin.domain.event.domain.vo.EventRegisterVO;
import lombok.Getter;

@Getter
public class EventJoinRequest {

    private Long memberId;
    private Long eventId;
    private int couponQuantity;

}
