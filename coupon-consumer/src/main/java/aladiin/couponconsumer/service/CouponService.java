package aladiin.couponconsumer.service;

import aladiin.core.domain.entity.IssuedCoupon;
import aladiin.core.domain.entity.dao.IssuedCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final IssuedCouponRepository issuedCouponRepository;

    public void issue(IssuedCoupon issuedCoupon) {
        issuedCouponRepository.save(issuedCoupon);
    }
}
