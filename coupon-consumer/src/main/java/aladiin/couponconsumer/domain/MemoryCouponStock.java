package aladiin.couponconsumer.domain;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryCouponStock {

    private Map<String, Long> couponStocks;

    public MemoryCouponStock() {
        this.couponStocks = new ConcurrentHashMap<>();
    }

    public long getQuantity(String eventKey) {
        if (!couponStocks.containsKey(eventKey)) return 0;
        return couponStocks.get(eventKey);
    }

    public void addStock(String eventKey, long quantity) {
        couponStocks.put(eventKey, couponStocks.get(eventKey) + quantity);
    }

    public void decrease(String eventKey) {
        couponStocks.put(eventKey, couponStocks.get(eventKey) - 1);
    }
}
