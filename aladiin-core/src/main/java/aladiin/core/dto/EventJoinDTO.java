package aladiin.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class EventJoinDTO {
    @JsonProperty("memberId")
    private Long memberId;
    @JsonProperty("eventId")
    private Long eventId;
    @JsonProperty("eventDate")
    private String eventDate;

}
