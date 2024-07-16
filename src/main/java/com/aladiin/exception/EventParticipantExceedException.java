package com.aladiin.exception;

public class EventParticipantExceedException extends Exception {

    private static final String message = "이벤트 참여자가 정원을 초과하였습니다";

    public EventParticipantExceedException() {
        super(message);
    }
}
