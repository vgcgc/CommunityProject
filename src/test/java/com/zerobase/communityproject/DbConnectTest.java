package com.zerobase.communityproject;

import com.zerobase.communityproject.entity.Member;
import com.zerobase.communityproject.entity.Post;
import com.zerobase.communityproject.model.request.CreatePostRequest;
import com.zerobase.communityproject.repository.MemberRepository;
import com.zerobase.communityproject.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DbConnectTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostService postService;

    // 테스트 로직
    @Test
    @DisplayName("Member Create")
    void insertMemberTest(){
        Member member = new Member("id", "pw", "name", "USER");
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        assertFalse(members.isEmpty());
    }

    @Test
    @DisplayName("Post Create")
    void createPostTest() {
        // given
        String title = "타이틀";
        // when
        CreatePostRequest createPostRequest = new CreatePostRequest(title, "내용");
        Post post = postService.createPost("test", createPostRequest);
        // then
        assertEquals(title, post.getTitle());
    }

}
