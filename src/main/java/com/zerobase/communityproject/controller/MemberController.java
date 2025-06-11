package com.zerobase.communityproject.controller;

import com.zerobase.communityproject.model.request.JoinRequest;
import com.zerobase.communityproject.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/join")
  public ResponseEntity<String> join(@Valid @RequestBody JoinRequest request) {
    return ResponseEntity.ok(memberService.join(request));
  }

  @PutMapping("/{name}")
  public ResponseEntity<?> updateName(@PathVariable String name) {
    return ResponseEntity.ok(memberService.updateMemberName(name));
  }

  @DeleteMapping
  public ResponseEntity<?> deleteMember() {
    memberService.deleteMember(memberService.getId());
    return ResponseEntity.ok(HttpStatus.OK);
  }
}
