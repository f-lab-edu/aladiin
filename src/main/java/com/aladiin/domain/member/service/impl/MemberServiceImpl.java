package com.aladiin.domain.member.service.impl;

import com.aladiin.domain.coupon.domain.entity.IssuedCoupon;
import com.aladiin.domain.member.domain.entity.Member;
import com.aladiin.domain.member.repository.MemberRepository;
import com.aladiin.domain.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void saveMember(Member member) {
        memberRepository.save(member);
    }
}
