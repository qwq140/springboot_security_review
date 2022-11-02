package com.example.springboot_security_review.modules.auth.domain.dto.request;

import com.example.springboot_security_review.modules.member.domain.entity.MemberEntity;
import lombok.Data;

@Data
public class JoinReqDto {
    private String username;
    private String password;
    private String email;
    private String nickname;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .username(username)
                .email(email)
                .nickname(nickname)
                .build();
    }

}
