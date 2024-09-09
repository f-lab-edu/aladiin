package aladiin.core.domain.entity.dao;


import aladiin.core.domain.entity.CouponStock;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponStockRepository {

    private final RedissonClient redissonClient;

    public void save(CouponStock couponStock) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(couponStock.getKey());
        atomicLong.set(couponStock.getQuantity());
    }
}
