package com.zerobase.communityproject.model.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class CommentRequest {

  @NotBlank(message = "게시글 제목은 필수입니다.")
  private String postTitle;

  @NotBlank(message = "게시글 작성자는 필수입니다.")
  private String postWriter;

  @NotBlank(message = "댓글 작성자는 필수입니다.")
  private String user;

  @NotBlank(message = "댓글 내용은 공백일 수 없습니다.")
  private String text;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;
}
