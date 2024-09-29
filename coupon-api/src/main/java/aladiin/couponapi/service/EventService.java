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

        EventJoinMember eventJoinMember = EventJoinMember.of(eventId, eventDate, memberId);
        boolean eventJoinStatus = eventJoinRepository.findMember(eventJoinMember.getKey());

        return EventJoinStatus.from(eventJoinStatus);
    }


}
