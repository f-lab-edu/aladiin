package com.aladiin.couponconsumer.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class EventParticipants {
    private Map<String, Set<Long>> participants;

    public EventParticipants() {
        participants = new HashMap<>();
    }

    public boolean containsMember(Long memberId, Long eventId, String eventDate) {
        String eventKey = getKey(eventId, eventDate);
        if(participants.containsKey(eventKey)) return participants.get(eventKey).contains(memberId);
        return false;
    }

    private String getKey(Long eventId, String eventDate) {
        return eventId + ":" + eventDate;
    }
}
