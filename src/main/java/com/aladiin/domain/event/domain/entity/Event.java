package com.aladiin.domain.event.domain.entity;

import com.aladiin.domain.coupon.domain.entity.Coupon;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    @Id @GeneratedValue
    @Column(name = "EVENT_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUPON_ID")
    private Coupon coupon;

    private int couponQuantity;

    private LocalDateTime startDatetime;

    private LocalDateTime endDatetime;

    public static Event of(Coupon coupon, int couponQuantity, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        return new Event(null, coupon, couponQuantity, startDatetime, endDatetime);
    }

}
