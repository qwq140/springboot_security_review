package com.example.springboot_security_review.modules.board.domain.dto.request;

import com.example.springboot_security_review.modules.board.domain.entity.BoardEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardSaveReqDto {

    private MultipartFile file;
    private String title;
    private String content;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .build();
    }

}
