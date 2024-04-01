package com.example.jpa.notice.model;

import lombok.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ResponseError> of(List<ObjectError> errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors != null) {
            errors.forEach((error) -> {
                responseErrorList.add(ResponseError.of((FieldError) error));
            });
        }

        return responseErrorList;
    }
}
