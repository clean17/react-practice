package com.example.springbootserver.core.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.springbootserver.core.exception.Exception400;


@Aspect
@Component
public class MyValidAdvice {
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping() {
    }

    @Before("postMapping() || putMapping()")
    public void validationAdvice(JoinPoint jp) {
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;

                if (errors.hasErrors()) {
                    throw new Exception400(
                            errors.getFieldErrors().get(0).getField(), // 오류 발생 필드
                            errors.getFieldErrors().get(0).getDefaultMessage() // 오류 메세지 ( message = ) e.getMessage() 에 사용
                    );
                }
            }
        }
    }
}