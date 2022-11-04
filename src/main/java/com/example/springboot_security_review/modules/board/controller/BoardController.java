package com.example.springboot_security_review.modules.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/board")
public class BoardController {

    @GetMapping
    public String list(){
        return "board/board_list";
    }

    @GetMapping("/write")
    public String writeForm(){
        return "board/board_form";
    }


}
