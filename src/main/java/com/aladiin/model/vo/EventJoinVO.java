package com.aladiin.model.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class EventJoinVO {

    private String key;
    private Long memberId;

    public static EventJoinVO of(Long eventId, Long memberId) {
        String eventTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String key = "coupon:event:" + eventId + ":date:"+eventTime+ ":issued:members";
        return new EventJoinVO(key, memberId);
    }
}
