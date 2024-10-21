package com.zerobase.communityproject.controller;

import com.zerobase.communityproject.model.request.CommentRequest;
import com.zerobase.communityproject.service.CommentService;
import com.zerobase.communityproject.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final MemberService memberService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> newComment (@Valid @RequestBody CommentRequest commentRequest) {
        commentRequest.setUser(memberService.getId());
        return ResponseEntity.ok(commentService.createComment(commentRequest));
    }

    @PutMapping
    public ResponseEntity<?> updateComment (@RequestBody @Valid CommentRequest commentRequest) {
        commentRequest.setUser(memberService.getId());
        return ResponseEntity.ok(commentService.updateComment(commentRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment (@RequestBody CommentRequest commentRequest) {
        commentRequest.setUser(memberService.getId());
        return ResponseEntity.ok(commentService.deleteComment(commentRequest));
    }
}
