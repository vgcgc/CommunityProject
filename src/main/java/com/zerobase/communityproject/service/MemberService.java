package com.zerobase.communityproject.service;

import com.zerobase.communityproject.domain.Member;
import com.zerobase.communityproject.exception.CustomException;
import com.zerobase.communityproject.exception.ErrorCode;
import com.zerobase.communityproject.model.request.JoinRequest;
import com.zerobase.communityproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public String join(JoinRequest request) {

    String id = request.getId();
    String pw = bCryptPasswordEncoder.encode(request.getPw());

    // id 중복 확인
    if (memberRepository.existsById(id)) {
      throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.DUPLICATE_ID);
    }
    Member member = new Member(id, pw, request.getName(), "USER");
    memberRepository.save(member);
    return id;

  }

  public String updateMemberName(String newName) {
    Long idx = getUserIdx(getId());
    Member member = memberRepository.findById(idx)
        .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.MEMBER_NOT_FOUND));
    member.setName(newName);
    memberRepository.save(member);
    return "이름 변경 성공";
  }

  public void deleteMember(String id) {
    memberRepository.deleteMemberById(id);
  }

  public String getId() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }

  public Long getUserIdx(String id) {
    Member member = memberRepository.findById(id);
    if (member == null) {
      throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.WRITER_IS_NOT_FOUND);
    }
    return memberRepository.findById(id).getIdx();
  }

}
