package com.zerobase.communityproject.model.response;

import com.zerobase.communityproject.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostComment {

    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private List<CommentDto> comments;

    public PostComment(Post post, List<CommentDto> comments) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getWriter();
        this.createdAt = post.getCreatedAt();
        this.comments = comments;
    }

}
