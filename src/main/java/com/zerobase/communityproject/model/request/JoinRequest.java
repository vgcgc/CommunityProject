package com.zerobase.communityproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JoinRequest {

    private String id;
    private String pw;
    private String name;
}
