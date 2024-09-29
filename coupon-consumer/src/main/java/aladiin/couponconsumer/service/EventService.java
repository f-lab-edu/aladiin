package aladiin.couponconsumer.service;

import aladiin.core.domain.entity.EventJoinMember;
import aladiin.couponconsumer.domain.EventParticipants;
import aladiin.couponconsumer.error.DuplicateJoinException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventParticipants eventParticipants;

    public void checkDuplicateJoin(EventJoinMember eventJoinMember) throws DuplicateJoinException {
        if (eventParticipants.containsMember(eventJoinMember)) {
            throw new DuplicateJoinException();
        }
    }

    public void join(Long memberId, Long eventId, String eventDate) {

    }
}
