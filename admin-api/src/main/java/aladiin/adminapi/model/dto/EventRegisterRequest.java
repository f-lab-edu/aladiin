package aladiin.adminapi.model.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import aladiin.core.domain.entity.Coupon;
import aladiin.core.domain.entity.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventRegisterRequest {

    @NotNull(message = "쿠폰 ID[couponId]는 필수 항목입니다")
    private Long couponId;

    @NotNull(message = "쿠폰 수량[couponQuantity]은 필수 항목입니다")
    @Positive(message = "쿠폰 수량[couponQuantity]은 1 이상의 양수입니다")
    private int couponQuantity;

    @NotBlank(message = "이벤트 시작일시[startDatetime]는 필수 항목입니다")
    private String startDatetime;

    @NotBlank(message = "이벤트 종료일시[endDatetime]는 필수 항목입니다")
    private String endDatetime;

    @AssertTrue(message = "이벤트 시작일시[startDatetime]가 올바르지 않습니다")
    public boolean isValidStartDatetime() {
        try {
            LocalDateTime.parse(startDatetime , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @AssertTrue(message = "이벤트 종료일시[startDatetime]가 올바르지 않습니다")
    public boolean isValidEndDatetime() {
        try {
            LocalDateTime.parse(endDatetime , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @AssertTrue(message = "이벤트 시작일시[startDatetime]는 이벤트 종료일시[startDatetime]보다 앞서야 합니다")
    public boolean isStartDateTimeAheadOfEndDateTime() {
        try {
            LocalDateTime startDateTime = LocalDateTime.parse(startDatetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime endDateTime = LocalDateTime.parse(endDatetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            if(endDateTime.isBefore(startDateTime)) return false;
        } catch (DateTimeParseException e) {
        }
        return true;
    }

    public Event toEntity(Coupon coupon) {
        return Event.of(coupon, couponQuantity
                , LocalDateTime.parse(startDatetime , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                , LocalDateTime.parse(endDatetime , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}