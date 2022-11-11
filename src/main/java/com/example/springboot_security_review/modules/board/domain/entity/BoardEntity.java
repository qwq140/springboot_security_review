package com.example.springboot_security_review.modules.board.domain.entity;

import com.example.springboot_security_review.modules.board.domain.dto.response.BoardDto;
import com.example.springboot_security_review.modules.file.domain.entity.FileEntity;
import com.example.springboot_security_review.modules.member.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_idx", referencedColumnName = "idx")
    private MemberEntity member;

    @OneToOne
    @JoinColumn(name = "file_idx", referencedColumnName = "idx")
    private FileEntity file;

    @CreationTimestamp
    private LocalDateTime createDate;

    public BoardDto toDto(){
        return BoardDto.builder()
                .title(title)
                .content(content)
                .member(member.toDto())
                .file(file.toDto())
                .build();
    }
}
