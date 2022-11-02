package com.example.springboot_security_review.config.auth;

import com.example.springboot_security_review.modules.member.domain.entity.MemberEntity;
import com.example.springboot_security_review.modules.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 로그인이 호출되면 자동실행되어 username이 DB에 있는지 확인한다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("로그인진행");
        System.out.println(username);
        MemberEntity principal = memberRepository.findByUsername(username);
        System.out.println(principal);

        if(principal == null) {
            return null;
        }

        return new PrincipalDetails(principal);
    }

}
