package com.aladiin.dao;

import com.aladiin.model.entity.Coupon;
import com.aladiin.model.entity.IssuedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("select ic " +
            "from IssuedCoupon ic " +
            "join fetch ic.member " +
            "join fetch ic.coupon " +
            "where ic.member.id = :memberId and ic.coupon.validDateTime > local_datetime and ic.useDatetime is null")
    List<IssuedCoupon> findValidIssuedCouponsByMemberId(Long memberId);
}
