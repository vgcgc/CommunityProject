package com.zerobase.communityproject.model.response;

import com.zerobase.communityproject.entity.Comment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentDto {

  private String text;
  private String writer;
  private LocalDateTime createdAt;

  public CommentDto(Comment comment) {
    this.text = comment.getText();
    this.writer = comment.getMember().getId();
    this.createdAt = comment.getCreatedDate();
  }
}
