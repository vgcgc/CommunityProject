package com.zerobase.communityproject.model.response;

import com.zerobase.communityproject.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class PostComment {

  private final String title;
  private final String content;
  private final String writer;
  private final LocalDateTime createdAt;
  private final List<CommentDto> comments;

  public PostComment(Post post, List<CommentDto> comments) {
    this.title = post.getTitle();
    this.content = post.getContent();
    this.writer = post.getMember().getName();
    this.createdAt = post.getCreatedDate();
    this.comments = comments;
  }

}
