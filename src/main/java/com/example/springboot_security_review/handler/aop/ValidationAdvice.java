package com.example.springboot_security_review.handler.aop;

import com.example.springboot_security_review.handler.exception.CustomValidationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class ValidationAdvice {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping(){}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping(){}

    @Before("postMapping() || putMapping()")
    public void apiAdvice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg: args) {
            if (arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()){
                    Map<String, String> errorMap = new HashMap<>();
                    for(FieldError error : bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomValidationException(bindingResult.getFieldError().getDefaultMessage(), errorMap);
                }
            }
        }
    }
}
