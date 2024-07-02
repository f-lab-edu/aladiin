package com.aladiin.domain.event.exception;

public class EventAlreadyJoinedException extends Exception {
    public static final String message = "이벤트에 이미 참여하였습니다";

    public EventAlreadyJoinedException() {
        super(message);
    }
}
