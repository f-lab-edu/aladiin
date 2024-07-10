package com.aladiin.exception;

public class NoSuchCouponExistException extends Exception {

    private static final String message = "해당 쿠폰이 존재하지 않습니다";

    public NoSuchCouponExistException() {
        super(message);
    }
}
