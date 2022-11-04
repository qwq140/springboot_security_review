package com.example.springboot_security_review.modules.file.domain.entity;

import com.example.springboot_security_review.eunms.FileType;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
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
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_file")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Builder.Default
    @JsonIgnoreProperties({"file"})
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "file")
    private List<FileListEntity> files = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FileType category;

    @CreationTimestamp
    private LocalDateTime createDate;

    public FileDto toDto(){
        return FileDto.builder()
                .idx(idx)
                .category(category)
                .files(files.stream().map(FileListEntity::toDto).collect(Collectors.toList()))
                .build();
    }
}