package com.aladiin.domain.member.domain;


import com.aladiin.domain.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

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

    public void changeMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }



}
