package com.zerobase.communityproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

  private final HttpStatus status;
  private final String code;

  public CustomException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.status = errorCode.getStatus();
    this.code = errorCode.getCode();
  }
}
