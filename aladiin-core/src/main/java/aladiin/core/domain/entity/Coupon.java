package aladiin.core.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import aladiin.core.domain.entity.common.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPON_ID")
    private Long id;

    @Column(name = "COUPON_NAME")
    private String couponName;

    @Embedded
    private Discount discount;

    @Column(name = "VALID_DATETIME")
    private LocalDateTime validDateTime;
}
