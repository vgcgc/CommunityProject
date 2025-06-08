package com.zerobase.communityproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  TEST_FAIL(HttpStatus.BAD_REQUEST, "TEST_FAIL", "실패 테스트입니다."),
  DUPLICATE_ID(HttpStatus.BAD_REQUEST, "DUPLICATE_ID", "같은 아이디가 존재합니다."),
  LOGIN_FAIL(HttpStatus.BAD_REQUEST, "LOGIN_FAIL", "입력한 정보가 잘못되었습니다."),
  TITLE_IS_EMPTY(HttpStatus.BAD_REQUEST, "TITLE_IS_EMPTY", "타이틀이 없습니다."),
  TITLE_IS_DUPLICATE(HttpStatus.BAD_REQUEST, "TITLE_IS_DUPLICATE", "타이틀이 중복입니다."),
  POST_IS_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_IS_NOT_FOUND", "해당 포스트가 없습니다."),
  WRITER_IS_NOT_FOUND(HttpStatus.NOT_FOUND, "WRITER_IS_NOT_FOUND", "요청된 작성자가 없습니다."),
  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_NOT_FOUND", "아이디가 없습니다.");

  private final HttpStatus status;
  private final String code;
  private final String message;

}