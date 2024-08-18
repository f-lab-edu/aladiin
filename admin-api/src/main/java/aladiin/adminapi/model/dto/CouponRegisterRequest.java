package aladiin.adminapi.model.dto;

import aladiin.adminapi.exception.InvalidDiscountException;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import aladiin.core.domain.entity.Coupon;
import aladiin.core.domain.entity.Discount;
import aladiin.core.domain.enums.DiscountType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CouponRegisterRequest {

    @NotBlank(message = "쿠폰명[couponName]은 필수 항목입니다")
    private String couponName;

    @NotBlank(message = "할인 타입[discountType]은 필수 항목입니다")
    private String discountType;

    @Positive(message = "할인 값[discountValue]은 음수가 될 수 없습니다")
    private int discountValue;

    @NotBlank(message = "유효 기간[validDateTime]은 필수 항목입니다")
    private String validDateTime;

    @AssertTrue(message = "할인 타입[discountType]이 올바르지 않습니다")
    public boolean isValidDiscountType() {
        return Arrays.asList(DiscountType.getTypes()).contains(discountType);
    }

    @AssertTrue(message = "할인 비율[discountValue]이 올바르지 않습니다")
    public boolean isValidDiscountRatio() {
        if (DiscountType.RATIO.getType().equals(discountType)) {
            if (discountValue > 100) return false;
        }
        return true;
    }

    @AssertTrue(message = "유효 기간[validDateTime]이 올바르지 않습니다")
    public boolean isValidValidDateTime() {
        try {
            LocalDateTime.parse(validDateTime , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public Coupon toEntity() throws InvalidDiscountException {

        Discount discount = Discount.of(DiscountType.getDiscountType(discountType), discountValue);

        return Coupon.builder()
                .couponName(couponName)
                .validDateTime(LocalDateTime.parse(validDateTime ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .discount(discount)
                .build();
    }
}
