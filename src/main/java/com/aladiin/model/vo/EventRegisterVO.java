package com.aladiin.model.vo;

import com.aladiin.model.entity.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
