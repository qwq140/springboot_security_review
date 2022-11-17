package com.example.springboot_security_review.modules.file.domain.entity;

import com.example.springboot_security_review.eunms.FileType;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileListDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_file_list")
public class FileListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "file_master_idx")
    private FileEntity file;

    private String originalName;
    private String extension;
    private Long size;
    private String url;
    private String path;

    @CreationTimestamp
    private LocalDateTime createDate;

    public FileListDto toDto(){
        return FileListDto.builder()
                .idx(idx)
                .extension(extension)
                .url(url)
                .originalName(originalName)
                .build();
    }

}