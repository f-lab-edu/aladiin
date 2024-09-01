package aladiin.core.domain.entity;

public class EventJoinMember {

    public static String getKey(Long eventId, String eventDate, Long memberId) {
       return "event:" + eventId
                + "date:" + eventDate
                + "member" + memberId;
    }
}
