package aladiin.couponconsumer.service;

import aladiin.core.domain.entity.Member;
import aladiin.core.domain.entity.dao.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }
}