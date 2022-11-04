package com.example.springboot_security_review.modules.file.domain.dto.response;

import com.example.springboot_security_review.eunms.FileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private Long idx;
    private FileType category;
    private List<FileListDto> files;


}
