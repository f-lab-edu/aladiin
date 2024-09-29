package aladiin.adminapi.service;

import aladiin.core.domain.entity.dao.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import aladiin.core.domain.entity.Event;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }


}