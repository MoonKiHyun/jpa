package com.example.jpa.notice.model;

import lombok.*;
import org.springframework.validation.FieldError;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {

    private String field;
    private String message;

    public static ResponseError of(FieldError error) {

        return ResponseError.builder()
                .field((error).getField())
                .message(error.getDefaultMessage())
                .build();
    }
}
