package com.example.springboot_security_review.modules.auth.service;

import com.example.springboot_security_review.eunms.MemberRoleType;
import com.example.springboot_security_review.modules.auth.domain.dto.request.JoinReqDto;
import com.example.springboot_security_review.modules.member.domain.dto.response.MemberDto;
import com.example.springboot_security_review.modules.member.domain.entity.MemberEntity;
import com.example.springboot_security_review.modules.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberDto join(JoinReqDto joinReqDto) {
        MemberEntity member = joinReqDto.toEntity();
        String encPassword = bCryptPasswordEncoder.encode(joinReqDto.getPassword());
        member.setPassword(encPassword);
        member.setRole(MemberRoleType.USER);

        MemberEntity memberEntity = memberRepository.save(member);
        return memberEntity == null ? null : memberEntity.toDto();
    }

}
