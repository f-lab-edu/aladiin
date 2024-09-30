package aladiin.couponconsumer.error;


import aladiin.core.domain.entity.EventJoinMember;
import aladiin.core.request.EventJoinRequest;

public class DuplicateJoinException extends Exception {
    public DuplicateJoinException(String message) {
        super(message);
    }

    public DuplicateJoinException(EventJoinMember eventJoinMember) {
        this(eventJoinMember.getKey());
    }
}
