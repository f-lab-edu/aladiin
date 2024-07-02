package com.aladiin.domain.event.exception;

public class NoSuchEventExistException extends Exception {

    private static final String message = "해당 이벤트가 존재하지 않습니다";

    public NoSuchEventExistException() {
        super(message);
    }
}
