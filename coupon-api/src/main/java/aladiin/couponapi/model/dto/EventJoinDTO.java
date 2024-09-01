package aladiin.couponapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class EventJoinDTO {
    private Long userId;
    private Long eventId;
    private String eventDate;

}
