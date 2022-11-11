package com.example.springboot_security_review.modules.board.domain.dto.response;

import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.member.domain.dto.response.MemberDto;
import com.example.springboot_security_review.modules.member.domain.entity.MemberEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDto {
    private String title;
    private String content;
    private FileDto file;
    private MemberDto member;
}
