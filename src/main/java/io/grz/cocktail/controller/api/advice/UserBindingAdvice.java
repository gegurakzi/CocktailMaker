package io.grz.cocktail.controller.api.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@Aspect
public class UserBindingAdvice {


    @Around("execution(* io.grz.cocktail.controller.api..*Controller.*(..))")
    public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method = proceedingJoinPoint.getSignature().getName();

        for(Object arg : args){
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;
                if(bindingResult.hasErrors()){
                    Map<String, String> errorMap = new HashMap<>();
                    for(FieldError error:bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(), error.getDefaultMessage());
                        log.warn(type+"."+method+"() => Field: "+error.getField()+", message: "+error.getDefaultMessage());
                    }
                    return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
