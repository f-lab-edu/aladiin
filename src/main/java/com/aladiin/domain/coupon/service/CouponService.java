package com.aladiin.domain.coupon.service;

import com.aladiin.domain.coupon.domain.entity.Coupon;
import com.aladiin.domain.coupon.domain.entity.IssuedCoupon;
import com.aladiin.domain.coupon.exception.NoSuchCouponExistException;

import java.util.List;

public interface CouponService {
    void saveCoupon(Coupon entity);

    Coupon findCouponById(Long couponId) throws NoSuchCouponExistException;

    List<IssuedCoupon> findValidIssuedCoupons(Long memberId);
}
