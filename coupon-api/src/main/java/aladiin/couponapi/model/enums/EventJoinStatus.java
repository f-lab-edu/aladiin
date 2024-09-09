package aladiin.couponapi.model.enums;

public enum EventJoinStatus {

    JOINED, NOT_JOINED;

    public static EventJoinStatus from(boolean eventJoinStatus){
        if(eventJoinStatus) return JOINED;
        return NOT_JOINED;
    }
}
