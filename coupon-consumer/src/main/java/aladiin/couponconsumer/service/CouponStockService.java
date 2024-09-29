package aladiin.couponconsumer.service;

import aladiin.core.domain.entity.CouponStock;
import aladiin.core.domain.entity.dao.CouponStockRepository;
import aladiin.couponconsumer.config.CouponConfigurationProperties;
import aladiin.couponconsumer.domain.MemoryCouponStock;
import org.springframework.stereotype.Service;

@Service
public class CouponStockService {

    private final CouponStockRepository couponStockRepository;
    private final MemoryCouponStock memoryCouponStock;
    private final int restockQuantity;

    public CouponStockService(CouponStockRepository couponStockRepository,
                              MemoryCouponStock memoryCouponStock,
                              CouponConfigurationProperties configurationProperties) {
        this.couponStockRepository = couponStockRepository;
        this.memoryCouponStock = memoryCouponStock;
        this.restockQuantity = configurationProperties.getRestockQuantity();
    }

    public synchronized void restock(Long eventId, String eventDate) {

        String eventKey = eventId + eventDate;
        CouponStock couponStock = CouponStock.of(eventKey, restockQuantity);

        if (memoryCouponStock.getQuantity(eventKey) == 0) {
            long foundStockQuantity = couponStockRepository.reduce(couponStock);
            memoryCouponStock.addStock(eventKey, foundStockQuantity);
        }
    }

    public void decrease(Long eventId, String eventDate) {
        String eventKey = eventId + eventDate;
        memoryCouponStock.decrease(eventKey);
    }
}
