package com.zerobase.communityproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    TEST_FAIL("TEST_FAIL", "실패 테스트입니다."),
    LOGIN_FAIL("LOGIN_FAIL","입력한 정보가 잘못되었습니다.");

    private final String code;
    private final String message;

}