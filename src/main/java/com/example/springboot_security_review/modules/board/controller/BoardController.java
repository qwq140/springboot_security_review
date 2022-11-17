package com.example.springboot_security_review.modules.board.controller;

import com.example.springboot_security_review.config.auth.PrincipalDetails;
import com.example.springboot_security_review.eunms.FileType;
import com.example.springboot_security_review.modules.board.domain.dto.request.BoardSaveReqDto;
import com.example.springboot_security_review.modules.board.domain.dto.response.BoardDto;
import com.example.springboot_security_review.modules.board.service.BoardService;
import com.example.springboot_security_review.modules.file.domain.dto.response.FileDto;
import com.example.springboot_security_review.modules.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/board")
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping
    public String list(){
        return "board/board_list";
    }

    @GetMapping("/write")
    public String writeForm(){
        return "board/board_form";
    }

    @PostMapping("/write")
    public String write(BoardSaveReqDto boardSaveReqDto, HttpServletRequest request, @AuthenticationPrincipal PrincipalDetails principalDetails){

        FileDto fileDto = null;

        if (boardSaveReqDto.getFile() != null) {
            fileDto = fileService.saveFile(boardSaveReqDto.getFile(), FileType.BOARD);
            if(fileDto == null) {
                // 파일 업로드 실패
                return "redirect:/v1/board/write";
            }
        }
        BoardDto boardDto = boardService.save(boardSaveReqDto, principalDetails, fileDto);
        if(boardDto == null){
            // Board 업로드 실패
            return "redirect:/v1/board/write";
        }
        return "redirect:/v1/board";
    }

}
