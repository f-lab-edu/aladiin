package aladiin.adminapi.service;

import aladiin.adminapi.exception.NoSuchCouponExistException;
import aladiin.core.domain.entity.dao.CouponRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import aladiin.core.domain.entity.Coupon;
import aladiin.core.domain.entity.IssuedCoupon;
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

    public Coupon findCouponById(Long couponId) throws NoSuchCouponExistException {
        return couponRepository.findById(couponId)
                .orElseThrow(NoSuchCouponExistException::new);
    }

    public List<IssuedCoupon> findValidIssuedCoupons(Long memberId) {
        return couponRepository.findValidIssuedCouponsByMemberId(memberId);
    }
}