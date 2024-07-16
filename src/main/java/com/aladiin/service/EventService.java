package com.aladiin.service;

import com.aladiin.model.entity.Coupon;
import com.aladiin.model.entity.IssuedCoupon;
import com.aladiin.dao.IssuedCouponRepository;
import com.aladiin.model.entity.Event;
import com.aladiin.model.vo.EventJoinVO;
import com.aladiin.model.dto.EventJoinDTO;
import com.aladiin.model.dto.EventJoinResultDTO;
import com.aladiin.exception.EventAlreadyJoinedException;
import com.aladiin.exception.EventParticipantExceedException;
import com.aladiin.exception.NoSuchEventExistException;
import com.aladiin.dao.EventRepository;
import com.aladiin.model.entity.Member;
import com.aladiin.exception.NoSuchMemberExistException;
import com.aladiin.dao.MemberRepository;
import com.aladiin.dao.TransactionalRedisRepository;
import com.aladiin.infra.kafka.producer.CouponIssueProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final IssuedCouponRepository issuedCouponRepository;

    private final TransactionalRedisRepository transactionalRedisRepository;

    private final CouponIssueProducer couponIssueProducer;

    public void saveEvent(Event event) {
        eventRepository.save(event);
//        redisRepository.saveEvent(EventRegisterVO.from(event));
    }

    public void join(EventJoinDTO eventJoinDTO) throws EventParticipantExceedException, NoSuchEventExistException, EventAlreadyJoinedException {

        EventJoinResultDTO eventJoinResultDTO =
                transactionalRedisRepository.countAndAdd(EventJoinVO.of(eventJoinDTO.getEventId(), eventJoinDTO.getMemberId()));
//        Event event = eventRepository.findById(eventJoinDTO.getEventId()).orElseThrow(NoSuchEventExistException::new).get();

        if (eventJoinResultDTO.getIsDuplicateJoin() == 0L) {
            throw new EventAlreadyJoinedException();
        }
        if (eventJoinResultDTO.getCurrentParticipantNumber() >= eventJoinDTO.getCouponQuantity()) {
            throw new EventParticipantExceedException();
        }
    }

    public void issueCoupon(EventJoinDTO eventJoinDTO) throws NoSuchEventExistException, NoSuchMemberExistException {
        Event event = Optional.of(eventRepository.findById(eventJoinDTO.getEventId())).orElseThrow(NoSuchEventExistException::new).get();
        Member member = Optional.of(memberRepository.findById(eventJoinDTO.getMemberId())).orElseThrow(NoSuchMemberExistException::new).get();
        Coupon coupon = event.getCoupon();

        IssuedCoupon issuedCoupon = IssuedCoupon.of(member, event, coupon);
        member.addIssuedCoupon(issuedCoupon);

        issuedCouponRepository.save(IssuedCoupon.of(member, event, coupon));
    }
}
