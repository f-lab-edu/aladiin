package aladiin.core.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventJoinMember {

    private String eventKey;
    private Long memberId;

    public static EventJoinMember of(Long eventId, String eventDate, Long memberId) {
        String eventKey = eventId + ":" + eventDate;
        return new EventJoinMember(eventKey, memberId);
    }

    public String getKey() {
        return eventKey + ":" + memberId;
    }
}
