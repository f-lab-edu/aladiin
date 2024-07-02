package com.aladiin.domain.member.domain.entity;


import com.aladiin.domain.coupon.domain.entity.IssuedCoupon;
import com.aladiin.domain.member.domain.MemberStatus;
import com.aladiin.domain.member.domain.MemberType;
import com.aladiin.domain.common.BaseTimeEntity;
import com.aladiin.domain.order.domain.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_STATUS")
    private MemberStatus memberStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_TYPE")
    private MemberType memberType;

    @OneToMany(mappedBy = "member")
    private List<IssuedCoupon> issuedCoupons;

    @OneToMany(mappedBy = "member")
    private List<Order> orders;

    public void changeMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public void addIssuedCoupon(IssuedCoupon issuedCoupon) {
        this.issuedCoupons.add(issuedCoupon);

    }



}
