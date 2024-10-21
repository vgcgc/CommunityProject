package com.zerobase.communityproject.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdatePostRequest {

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;
    private String content;
    private String newTitle;
}
