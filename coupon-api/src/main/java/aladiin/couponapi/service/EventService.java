package aladiin.couponapi.service;

import aladiin.core.domain.entity.EventJoinMember;
import aladiin.core.domain.entity.dao.EventJoinRepository;
import aladiin.couponapi.model.enums.EventJoinStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventJoinRepository eventJoinRepository;
    public EventJoinStatus getJoinStatus(Long eventId, String eventDate, Long memberId) {

        String key = EventJoinMember.getKey(eventId, eventDate, memberId);
        boolean eventJoinStatus = eventJoinRepository.findMember(key);

        return EventJoinStatus.from(eventJoinStatus);
    }


}
