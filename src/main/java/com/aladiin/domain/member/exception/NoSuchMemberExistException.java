package com.aladiin.domain.member.exception;

public class NoSuchMemberExistException extends Exception {

    private static final String message = "해당 회원이 존재하지 않습니다";

    public NoSuchMemberExistException() {
        super(message);
    }
}
