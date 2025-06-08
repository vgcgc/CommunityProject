package com.zerobase.communityproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

  private final HttpStatus status;
  private final String code;
  private final String message;

  public CustomException(ErrorCode errorCode) {
    this.status = errorCode.getStatus();
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
  }


}
