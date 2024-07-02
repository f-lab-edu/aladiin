package com.aladiin.domain.event.domain.vo;

import com.aladiin.domain.event.domain.entity.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class EventRegisterVO {

    private String key;
    private int couponQuantity;

    public static EventRegisterVO from(Event event) {
        String key = "coupon:event:" + event.getId() + ":coupon-quantity";
        return new EventRegisterVO(key, event.getCouponQuantity());
    }
}
