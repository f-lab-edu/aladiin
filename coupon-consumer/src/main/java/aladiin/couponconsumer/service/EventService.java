package aladiin.couponconsumer.service;

import aladiin.core.domain.entity.Event;
import aladiin.core.domain.entity.EventJoinMember;
import aladiin.core.domain.entity.dao.EventJoinRepository;
import aladiin.core.domain.entity.dao.EventRepository;
import aladiin.couponconsumer.domain.EventParticipants;
import aladiin.couponconsumer.error.DuplicateJoinException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventParticipants eventParticipants;
    private final EventRepository eventRepository;
    private final EventJoinRepository eventJoinRepository;

    public void checkDuplicateJoin(EventJoinMember eventJoinMember) throws DuplicateJoinException {
        if (eventParticipants.containsMember(eventJoinMember)) {
            throw new DuplicateJoinException(eventJoinMember);
        }
    }

    public void join(EventJoinMember eventJoinMember) {
        eventParticipants.addMember(eventJoinMember);
        eventJoinRepository.addMember(eventJoinMember.getKey());
    }

    public Event findById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow();
    }
}
