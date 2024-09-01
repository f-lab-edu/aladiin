package aladiin.core.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponStock {

    private String key;
    private int quantity;

    public static CouponStock of(Event event, LocalDate eventDate) {
        String key = "event:" + event.getId()
                + ":date:" + eventDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + ":coupon:stock";
        int quantity = event.getCouponQuantity();
        return new CouponStock(key, quantity);
    }
}
