package com.example.springboot_security_review.modules.auth.controller;

import com.example.springboot_security_review.modules.auth.domain.dto.request.JoinReqDto;
import com.example.springboot_security_review.modules.auth.service.AuthService;
import com.example.springboot_security_review.modules.member.domain.dto.response.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm(){
        return "auth/login";
    }

    @GetMapping("/join")
    public String joinForm(){
        return "auth/join";
    }

    @PostMapping("/join")
    public String join(@Valid JoinReqDto joinReqDto, BindingResult bindingResult) {
        authService.join(joinReqDto);
        return "redirect:/v1/auth/login";
    }

}
