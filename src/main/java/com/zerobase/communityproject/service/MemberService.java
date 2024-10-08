package com.zerobase.communityproject.service;

import com.zerobase.communityproject.domain.Member;
import com.zerobase.communityproject.exception.CustomException;
import com.zerobase.communityproject.exception.ErrorCode;
import com.zerobase.communityproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String join(Map<String, String> request){

        String id = request.get("id");
        String pw = bCryptPasswordEncoder.encode(request.get("pw"));

        // id 중복 확인
        if (memberRepository.existsById(id)){
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.DUPLICATE_ID);
        }
        Member member = new Member(id, pw);
        if (request.containsKey("name")){
            member.setName(request.get("name"));
        }
        member.setRole("USER");
        memberRepository.save(member);
        return member.getId();

    }

    public String getId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Long getUserIdx(String id) {
        return memberRepository.findById(id).getIdx();
    }

}
