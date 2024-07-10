package com.aladiin.model.entity;

import com.aladiin.model.enums.MemberStatus;
import com.aladiin.model.enums.MemberType;
import com.aladiin.model.entity.common.BaseTimeEntity;
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

    public void changeMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public void addIssuedCoupon(IssuedCoupon issuedCoupon) {
        this.issuedCoupons.add(issuedCoupon);

    }



}
