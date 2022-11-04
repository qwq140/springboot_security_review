package com.example.springboot_security_review.modules.file.domain.dto.response;

import com.example.springboot_security_review.modules.file.domain.entity.FileListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileListDto {
    private Long idx; // 파일 고유번호
    private String originalName; // 파일 원본이름
    private String extension; // 파일 확장자
    private String url; // 파일 경로
}
