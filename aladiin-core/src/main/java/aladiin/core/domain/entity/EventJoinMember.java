package aladiin.core.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventJoinMember {
    private String key;
    private Long value;

    public static EventJoinMember of(Long eventId, String eventDate, Long memberId) {
        return new EventJoinMember(eventId + ":" + eventDate, memberId);
    }
}
