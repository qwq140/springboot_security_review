package com.example.springboot_security_review.handler;

import com.example.springboot_security_review.handler.exception.CustomException;
import com.example.springboot_security_review.handler.exception.CustomValidationException;
import com.example.springboot_security_review.utils.Script;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {
        System.out.println("벨리데이션 핸들러");
        return Script.back(e.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    public String controllerException(CustomException e) {
        return Script.back(e.getMessage());
    }

}
