package aladiin.adminapi.service;

import aladiin.core.domain.entity.Coupon;
import aladiin.core.domain.entity.CouponStock;
import aladiin.core.domain.entity.dao.CouponStockRepository;
import aladiin.core.domain.entity.dao.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import aladiin.core.domain.entity.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final CouponStockRepository couponStockRepository;

    public void saveEvent(Event event) {
        eventRepository.save(event);
        saveCouponStock(event);
    }

    private void saveCouponStock(Event event) {
        LocalDate eventDate = event.getStartDatetime().toLocalDate();
        LocalDate endDate = event.getEndDatetime().toLocalDate();

        while (eventDate.isBefore(endDate) || eventDate.isEqual(endDate)){
            CouponStock couponStock = CouponStock.of(event, eventDate);
            couponStockRepository.save(couponStock);
            eventDate = eventDate.plusDays(1);
        }
    }
}