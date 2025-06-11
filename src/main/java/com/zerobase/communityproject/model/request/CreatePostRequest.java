package com.zerobase.communityproject.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CreatePostRequest {

  @NotBlank(message = "제목은 필수 입력값입니다.")
  private String title;

  @NotBlank(message = "내용은 필수 입력값입니다.")
  private String content;
}
