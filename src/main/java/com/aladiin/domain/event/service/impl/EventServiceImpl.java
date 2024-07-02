package com.aladiin.domain.event.service.impl;

import com.aladiin.domain.coupon.domain.entity.Coupon;
import com.aladiin.domain.coupon.domain.entity.IssuedCoupon;
import com.aladiin.domain.coupon.repository.CouponRepository;
import com.aladiin.domain.coupon.repository.IssuedCouponRepository;
import com.aladiin.domain.event.domain.entity.Event;
import com.aladiin.domain.event.domain.vo.EventJoinVO;
import com.aladiin.domain.event.domain.vo.EventRegisterVO;
import com.aladiin.domain.event.dto.EventJoinDTO;
import com.aladiin.domain.event.dto.EventJoinResultDTO;
import com.aladiin.domain.event.exception.EventAlreadyJoinedException;
import com.aladiin.domain.event.exception.EventParticipantExceedException;
import com.aladiin.domain.event.exception.NoSuchEventExistException;
import com.aladiin.domain.event.repository.EventRepository;
import com.aladiin.domain.event.service.EventService;
import com.aladiin.domain.member.domain.entity.Member;
import com.aladiin.domain.member.exception.NoSuchMemberExistException;
import com.aladiin.domain.member.repository.MemberRepository;
import com.aladiin.global.dao.redis.RedisRepository;
import com.aladiin.global.dao.redis.TransactionalRedisRepository;
import com.aladiin.infra.kafka.producer.CouponIssueProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final IssuedCouponRepository issuedCouponRepository;

    private final RedisRepository redisRepository;
    private final TransactionalRedisRepository transactionalRedisRepository;

    private final CouponIssueProducer couponIssueProducer;

    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);
//        redisRepository.saveEvent(EventRegisterVO.from(event));
    }

    @Override
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

    @Override
    public void issueCoupon(EventJoinDTO eventJoinDTO) throws NoSuchEventExistException, NoSuchMemberExistException {
        Event event = Optional.of(eventRepository.findById(eventJoinDTO.getEventId())).orElseThrow(NoSuchEventExistException::new).get();
        Member member = Optional.of(memberRepository.findById(eventJoinDTO.getMemberId())).orElseThrow(NoSuchMemberExistException::new).get();
        Coupon coupon = event.getCoupon();

        IssuedCoupon issuedCoupon = IssuedCoupon.of(member, event, coupon);
        member.addIssuedCoupon(issuedCoupon);

        issuedCouponRepository.save(IssuedCoupon.of(member, event, coupon));
    }
}
