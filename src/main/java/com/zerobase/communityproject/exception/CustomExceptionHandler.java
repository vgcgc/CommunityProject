package com.zerobase.communityproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<ErrorDto> handleException(CustomException ex) {
    log.error("CustomException occurred: code={}, message={}", ex.getCode(), ex.getMessage());
    return ErrorDto.toResponseEntity(ex);
  }
}
