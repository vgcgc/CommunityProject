package com.zerobase.communityproject;

import com.zerobase.communityproject.domain.Member;
import com.zerobase.communityproject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
public class dbConnectTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void insertMemberTest(){
        Member member = new Member("id", "pw", "name", "role");
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        assertFalse(members.isEmpty());
    }
}
