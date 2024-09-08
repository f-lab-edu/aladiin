package aladiin.couponapi.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EventJoinRequest {
    @NotNull(message = "회원 ID[memberId]는 필수 항목입니다")
    private Long memberId;
    @NotNull(message = "이벤트 ID[eventId]는 필수 항목입니다")
    private Long eventId;
    @NotBlank(message = "이벤트날짜[eventDate]는 필수 항목입니다")
    private String eventDate;

}
