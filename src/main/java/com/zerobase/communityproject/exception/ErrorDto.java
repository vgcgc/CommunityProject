package com.zerobase.communityproject.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorDto {

    private String code;
    private String msg;

    public static ResponseEntity<ErrorDto> toResponseEntity(CustomException ex) {

        return ResponseEntity
                .status(ex.getStatus())
                .body(ErrorDto.builder()
                        .code(ex.getCode())
                        .msg(ex.getMessage())
                        .build());
    }

}
