package com.example.springboot_security_review.modules.member.domain.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {
    private String username;
    private String email;
    private String nickname;
}
