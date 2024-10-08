package com.zerobase.communityproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    TEST_FAIL("TEST_FAIL", "실패 테스트입니다."),
    DUPLICATE_ID("DUPLICATE_ID", "같은 아이디가 존재합니다."),
    LOGIN_FAIL("LOGIN_FAIL","입력한 정보가 잘못되었습니다."),
    TITLE_IS_EMPTY("TITLE_IS_EMPTY", "타이틀이 없습니다."),
    TITLE_IS_DUPLICATE("TITLE_IS_DUPLICATE", "타이틀이 중복입니다.");

    private final String code;
    private final String message;

}