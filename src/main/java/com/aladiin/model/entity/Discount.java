package com.aladiin.model.entity;

import com.aladiin.exception.InvalidDiscountException;
import com.aladiin.exception.InvalidDiscountRatioException;
import com.aladiin.exception.InvalidDiscountValueException;
import com.aladiin.model.enums.DiscountType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Discount {

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private int discountValue;

    public static Discount of(DiscountType discountType, int discountValue) throws InvalidDiscountException {

        if (discountType.equals(DiscountType.VALUE)) {
            if (discountValue <= 0) throw new InvalidDiscountValueException();
        } else if (discountType.equals(DiscountType.RATIO)) {
            if (discountValue <= 0 || discountValue > 100) throw new InvalidDiscountRatioException();
        }

        return new Discount(discountType, discountValue);
    }
}
