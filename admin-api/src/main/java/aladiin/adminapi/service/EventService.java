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

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }


}