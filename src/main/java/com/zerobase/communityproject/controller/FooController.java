package com.zerobase.communityproject.controller;

import com.zerobase.communityproject.domain.Member;
import com.zerobase.communityproject.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FooController {

    @GetMapping("/")
    public String test(){
        return "확인";
    }

    @PostMapping("/test")
    public void tt(@RequestBody Map<String, String> text){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(1);
    }
}
