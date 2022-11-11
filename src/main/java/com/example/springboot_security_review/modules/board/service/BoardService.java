package com.example.springboot_security_review.modules.board.service;

import com.example.springboot_security_review.config.auth.PrincipalDetails;
import com.example.springboot_security_review.modules.board.domain.dto.request.BoardSaveReqDto;
import com.example.springboot_security_review.modules.board.domain.dto.response.BoardDto;
import com.example.springboot_security_review.modules.board.domain.entity.BoardEntity;
import com.example.springboot_security_review.modules.board.repository.BoardRepository;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardDto save(BoardSaveReqDto boardSaveReqDto, PrincipalDetails principalDetails, FileDto fileDto) {
        BoardEntity board = boardSaveReqDto.toEntity();
        board.setMember(principalDetails.getMember());
        if(fileDto != null){
            board.setFile(fileDto.toEntity());
        }
        return boardRepository.save(board).toDto();
    }

}
