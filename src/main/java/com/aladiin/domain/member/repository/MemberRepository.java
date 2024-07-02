package com.aladiin.domain.member.repository;

import com.aladiin.domain.coupon.domain.entity.IssuedCoupon;
import com.aladiin.domain.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
