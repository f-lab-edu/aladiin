package com.aladiin.service;


import com.aladiin.model.entity.Coupon;
import com.aladiin.model.entity.IssuedCoupon;
import com.aladiin.exception.NoSuchCouponExistException;
import com.aladiin.dao.CouponRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponService{

    private final CouponRepository couponRepository;

    public void saveCoupon(Coupon coupon) {
        couponRepository.save(coupon);
    }

    public Coupon findCouponById(Long couponId) throws NoSuchCouponExistException{
        return couponRepository.findById(couponId)
                .orElseThrow(NoSuchCouponExistException::new);
    }

    public List<IssuedCoupon> findValidIssuedCoupons(Long memberId) {
        return couponRepository.findValidIssuedCouponsByMemberId(memberId);
    }
}
