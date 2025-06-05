package com.zerobase.communityproject.controller;

import com.zerobase.communityproject.domain.Post;
import com.zerobase.communityproject.model.request.CreatePostRequest;
import com.zerobase.communityproject.model.request.UpdatePostRequest;
import com.zerobase.communityproject.service.MemberService;
import com.zerobase.communityproject.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  private final MemberService memberService;

  @GetMapping
  public ResponseEntity<?> getPostList(final Pageable pageable) {
    return ResponseEntity.ok(postService.getPostList(pageable));
  }

  @GetMapping("/{writer}")
  public ResponseEntity<Page<Post>> getMyPosts(@PathVariable String writer,
      final Pageable pageable) {
    return ResponseEntity.ok(postService.getMyPost(writer, pageable));
  }

  @GetMapping("/search")
  public ResponseEntity<Page<Post>> searchPosts(@RequestParam String title,
      final Pageable pageable) {
    return ResponseEntity.ok(postService.searchingPost(title, pageable));
  }

  @GetMapping("/info/{title}/{writer}")
  public ResponseEntity<?> postInfo(@PathVariable String title, @PathVariable String writer) {
    return ResponseEntity.ok(postService.getPostInfo(title, writer));
  }

  @PostMapping
  public ResponseEntity<Post> createPost(@Valid @RequestBody CreatePostRequest post) {
    String id = memberService.getId();
    return ResponseEntity.ok(postService.createPost(id, post));
  }

  @PutMapping
  public ResponseEntity<Post> updatePost(@Valid @RequestBody UpdatePostRequest post) {
    String id = memberService.getId();
    return ResponseEntity.ok(postService.updatePost(id, post));
  }

  @DeleteMapping
  public ResponseEntity<?> deletePost(@RequestBody String title, @RequestBody String writer) {
    postService.deletePost(title, writer);
    return ResponseEntity.ok(title + " - 삭제 되었습니다.");
  }
}