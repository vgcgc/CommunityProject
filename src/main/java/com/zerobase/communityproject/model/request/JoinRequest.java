package com.zerobase.communityproject.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JoinRequest {

  @NotBlank(message = "아이디는 필수 입력값입니다.")
  private String id;

  @NotBlank(message = "비밀번호는 필수 입력값입니다.")
  private String pw;

  @NotBlank(message = "이름은 필수 입력값입니다.")
  private String name;
}
