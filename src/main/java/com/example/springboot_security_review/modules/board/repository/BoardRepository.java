package com.example.springboot_security_review.modules.board.repository;


import com.example.springboot_security_review.modules.board.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
