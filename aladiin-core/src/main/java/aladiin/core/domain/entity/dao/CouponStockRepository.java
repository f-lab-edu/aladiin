package aladiin.core.domain.entity.dao;

import aladiin.core.domain.entity.CouponStock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CouponStockRepository {

    private final RedissonClient redissonClient;

    public void save(CouponStock couponStock) {
        RAtomicLong atomicLong = redissonClient.getAtomicLong(couponStock.getKey());
        atomicLong.set(couponStock.getQuantity());
    }

    public long reduce(CouponStock couponStock) {
        final String lockName = couponStock.getKey() + ":lock";
        final RLock lock = redissonClient.getLock(lockName);
        long stock = 0;

        try {
            if(!lock.tryLock(1, 3, TimeUnit.SECONDS)){
                RAtomicLong redisStock = redissonClient.getAtomicLong(couponStock.getKey());
                stock = redisStock.get();
                stock = Math.min(stock, couponStock.getQuantity());
                redisStock.addAndGet(-stock);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock != null && lock.isLocked()) {
                lock.unlock();
            }
            return stock;
        }
    }
}
