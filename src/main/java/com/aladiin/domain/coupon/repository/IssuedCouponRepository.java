package com.aladiin.domain.coupon.repository;

import com.aladiin.domain.coupon.domain.entity.IssuedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedCouponRepository extends JpaRepository<IssuedCoupon, Long> {
}
