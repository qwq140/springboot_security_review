package com.example.springboot_security_review.modules.auth.domain.dto.request;

import com.example.springboot_security_review.modules.member.domain.entity.MemberEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class JoinReqDto {

    @NotBlank(message = "유저네임을 입력하세요")
    @Size(max = 20, message = "입력길이를 초과하셨습니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    private String email;

    @NotBlank(message = "닉네임을 입력하세요")
    @Size(max = 10, message = "입력길이를 초과하셨습니다.")
    private String nickname;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .username(username)
                .email(email)
                .nickname(nickname)
                .build();
    }

}
