package aladiin.adminapi.service;

import aladiin.core.domain.entity.dao.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import aladiin.core.domain.entity.Member;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(Member member) {
        memberRepository.save(member);
    }
}
