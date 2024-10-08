package com.zerobase.communityproject.controller;

import com.zerobase.communityproject.domain.Post;
import com.zerobase.communityproject.service.MemberService;
import com.zerobase.communityproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> getMyPosts(@RequestParam String writer, final Pageable pageable) {
        return ResponseEntity.ok(postService.getMyPost(writer, pageable));
    }

    @GetMapping("/posts/search")
    public ResponseEntity<Page<Post>> searchingPosts(@RequestParam String title, final Pageable pageable) {
        return ResponseEntity.ok(postService.searchingPost(title, pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Map<String, String> post) {
        post.put("id", memberService.getId());
        return ResponseEntity.ok(postService.createPost(post));
    }

    @PutMapping("/update")
    public ResponseEntity<Post> updatePost(@RequestBody Map<String, String> post) {
        post.put("id", memberService.getId());
        return ResponseEntity.ok(postService.updatePost(post));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePost(@RequestBody String title, @RequestBody String writer) {
        postService.deletePost(title, writer);
        return ResponseEntity.ok(title + "- 삭제 되었습니다.");
    }
}