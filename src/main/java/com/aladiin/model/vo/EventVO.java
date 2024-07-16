package com.aladiin.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EventVO {
    private String key;

    public static EventVO from(String key) {
        return new EventVO(key);
    }
}
