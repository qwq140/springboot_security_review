package com.example.springboot_security_review.modules.member.domain.entity;

import com.example.springboot_security_review.eunms.MemberRoleType;
import com.example.springboot_security_review.modules.member.domain.dto.response.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(nullable = false, length = 100, unique = true)
    private String username;
    @Column(nullable = false, length = 100)
    private String nickname;
    @Column(nullable = false, length = 50)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRoleType role;

    @CreationTimestamp
    private Timestamp createDate;

    public MemberDto toDto(){
        return MemberDto.builder()
                .nickname(nickname)
                .email(email)
                .nickname(nickname)
                .build();
    }

}
