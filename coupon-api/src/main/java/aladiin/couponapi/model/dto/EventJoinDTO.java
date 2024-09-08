package aladiin.couponapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class EventJoinDTO {
    private Long memberId;
    private Long eventId;
    private String eventDate;

}
