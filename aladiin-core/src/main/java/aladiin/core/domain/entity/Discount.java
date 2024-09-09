package aladiin.core.domain.entity;

import aladiin.core.domain.enums.DiscountType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Discount {

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private int discountValue;

    public static Discount of(DiscountType discountType, int discountValue) {

//        if (discountType.equals(DiscountType.VALUE)) {
//            if (discountValue <= 0) throw new InvalidDiscountValueException();
//        } else if (discountType.equals(DiscountType.RATIO)) {
//            if (discountValue <= 0 || discountValue > 100) throw new InvalidDiscountRatioException();
//        }

        return new Discount(discountType, discountValue);
    }
}
