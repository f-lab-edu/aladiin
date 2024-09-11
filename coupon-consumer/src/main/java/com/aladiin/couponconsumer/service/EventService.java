package com.aladiin.couponconsumer.service;

import com.aladiin.couponconsumer.domain.EventParticipants;
import com.aladiin.couponconsumer.error.DuplicateJoinException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventParticipants eventParticipants;

    public void checkDuplicateJoin(Long memberId, Long eventId, String eventDate) throws DuplicateJoinException {
        if (eventParticipants.containsMember(memberId, eventId, eventDate)) {
            throw new DuplicateJoinException();
        }
    }

    public void join(Long memberId, Long eventId, String eventDate) {

    }
}
