package com.zerobase.communityproject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
// 에러 발생시 여기서 처리

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorDto> handleException(CustomException ex) {
        return ErrorDto.toResponseEntity(ex);
    }
}
