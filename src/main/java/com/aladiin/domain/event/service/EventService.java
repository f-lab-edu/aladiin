package com.aladiin.domain.event.service;

import com.aladiin.domain.event.domain.entity.Event;
import com.aladiin.domain.event.domain.vo.EventJoinVO;
import com.aladiin.domain.event.dto.EventJoinDTO;
import com.aladiin.domain.event.exception.EventAlreadyJoinedException;
import com.aladiin.domain.event.exception.EventParticipantExceedException;
import com.aladiin.domain.event.exception.NoSuchEventExistException;
import com.aladiin.domain.member.exception.NoSuchMemberExistException;

public interface EventService {
    void saveEvent(Event entity);

    void join(EventJoinDTO request) throws EventParticipantExceedException, NoSuchEventExistException, EventAlreadyJoinedException;

    void issueCoupon(EventJoinDTO eventJoinDTO) throws NoSuchEventExistException, NoSuchMemberExistException;
}
