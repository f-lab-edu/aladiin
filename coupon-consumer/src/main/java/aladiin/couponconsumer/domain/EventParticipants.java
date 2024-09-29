package aladiin.couponconsumer.domain;

import aladiin.core.domain.entity.EventJoinMember;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class EventParticipants {
    private Map<String, Set<Long>> participants;

    public EventParticipants() {
        participants = new HashMap<>();
    }

    public boolean containsMember(EventJoinMember eventJoinMember) {
        if(participants.containsKey(eventJoinMember.getEventKey())) {
            return participants.get(eventJoinMember.getEventKey()).contains(eventJoinMember.getMemberId());
        }
        return false;
    }

    public void addMember(EventJoinMember eventJoinMember) {
        participants.putIfAbsent(eventJoinMember.getEventKey(), new HashSet<>());
        participants.get(eventJoinMember.getEventKey()).add(eventJoinMember.getMemberId());
    }

    private String getKey(Long eventId, String eventDate) {
        return eventId + ":" + eventDate;
    }
}
