package com.zerobase.communityproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
// ErrorCode 의 객체 전환
    private final HttpStatus status;
    private final String code;
    private final String message;

    public CustomException(HttpStatus status, ErrorCode errorCode) {
        this.status = status;
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }


}
