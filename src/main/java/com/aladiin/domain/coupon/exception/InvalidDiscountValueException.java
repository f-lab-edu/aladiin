package com.aladiin.domain.coupon.exception;

public class InvalidDiscountValueException extends InvalidDiscountException {
    private static final String message = "할인 가격이 올바르지 않습니다";

    public InvalidDiscountValueException() {
        super(message);
    }

}
