package com.example.springboot_security_review.modules.file.domain.dto.response;

import com.example.springboot_security_review.eunms.FileType;
import com.example.springboot_security_review.modules.file.domain.entity.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private Long idx;
    private FileType category;
    private List<FileListDto> files;

    public FileEntity toEntity(){
        return FileEntity.builder()
                .idx(idx)
                .category(category)
                .files(files.stream().map(FileListDto::toEntity).collect(Collectors.toList()))
                .build();
    }

}
