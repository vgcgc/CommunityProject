package com.zerobase.communityproject.controller;

import com.zerobase.communityproject.exception.CustomException;
import com.zerobase.communityproject.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FooController {

    @GetMapping("/")
    public void test(){
        throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.TEST_FAIL);
    }
}
