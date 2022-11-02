package com.example.springboot_security_review.modules.member.repository;

import com.example.springboot_security_review.modules.member.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByUsername(String username);
}
