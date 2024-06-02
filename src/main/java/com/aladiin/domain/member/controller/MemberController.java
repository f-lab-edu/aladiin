package com.aladiin.domain.member.controller;

import com.aladiin.domain.member.domain.Member;
import com.aladiin.domain.member.dto.MemberSignUpRequest;
import com.aladiin.domain.member.service.MemberService;
import com.aladiin.domain.member.domain.MemberStatus;
import com.aladiin.domain.member.domain.MemberType;
import com.aladiin.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody MemberSignUpRequest request) {

        Member member = Member.builder()
                .memberName(request.getMemberName())
                .memberType(MemberType.GENERAL)
                .memberStatus(MemberStatus.ACTIVE)
                .build();

        memberService.saveMember(member);

        return ResponseEntity.ok(CommonResponse.ofSuccess());
    }

}
