package aladiin.adminapi.service;

import aladiin.core.domain.entity.CouponStock;
import aladiin.core.domain.entity.Event;
import aladiin.core.domain.entity.dao.CouponStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CouponStockService {

    public final CouponStockRepository couponStockRepository;

    public void saveCouponStock(Event event) {
        LocalDate eventDate = event.getStartDatetime().toLocalDate();
        LocalDate endDate = event.getEndDatetime().toLocalDate();

        while (eventDate.isBefore(endDate) || eventDate.isEqual(endDate)){
            CouponStock couponStock = CouponStock.of(event, eventDate);
            couponStockRepository.save(couponStock);
            eventDate = eventDate.plusDays(1);
        }
    }
}
