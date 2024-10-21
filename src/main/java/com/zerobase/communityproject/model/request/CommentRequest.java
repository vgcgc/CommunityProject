package com.zerobase.communityproject.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentRequest {

    private String postTitle;
    private String postWriter;
    private String user;
    @NotBlank(message = "댓글 내용은 공백일 수 없습니다.")
    private String text;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
