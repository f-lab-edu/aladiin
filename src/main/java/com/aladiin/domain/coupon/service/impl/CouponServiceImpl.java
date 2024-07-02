package com.aladiin.domain.coupon.service.impl;


import com.aladiin.domain.coupon.domain.entity.Coupon;
import com.aladiin.domain.coupon.domain.entity.IssuedCoupon;
import com.aladiin.domain.coupon.exception.NoSuchCouponExistException;
import com.aladiin.domain.coupon.repository.CouponRepository;
import com.aladiin.domain.coupon.service.CouponService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public void saveCoupon(Coupon coupon) {
        couponRepository.save(coupon);
    }

    @Override
    public Coupon findCouponById(Long couponId) throws NoSuchCouponExistException{
        return couponRepository.findById(couponId)
                .orElseThrow(NoSuchCouponExistException::new);
    }

    @Override
    public List<IssuedCoupon> findValidIssuedCoupons(Long memberId) {
        return couponRepository.findValidIssuedCouponsByMemberId(memberId);
    }
}
