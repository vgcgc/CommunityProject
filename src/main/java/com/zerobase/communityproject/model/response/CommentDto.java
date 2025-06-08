package com.zerobase.communityproject.model.response;

import com.zerobase.communityproject.entity.Comment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommentDto {

  private String text;
  private String writer;
  private LocalDateTime createdAt;

  public CommentDto(Comment comment) {
    this.text = comment.getText();
    this.writer = comment.getWriter();
    this.createdAt = comment.getCreatedAt();
  }
}
