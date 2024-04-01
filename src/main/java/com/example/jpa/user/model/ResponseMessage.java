package com.example.jpa.user.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMessage {

    private ResponseMessageHeader header;
    private Object body;

    public static ResponseMessage fail(String message) {
        return ResponseMessage.fail(message, null);
    }

    public static ResponseMessage fail(String message, Object data) {
        return ResponseMessage.builder()
                .header(ResponseMessageHeader.builder()
                        .result(false)
                        .resultCode("")
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build())
                .body(null)
                .build();
    }

    public static ResponseMessage success() {
        return ResponseMessage.success(null);
    }

    public static ResponseMessage success(Object data) {
        return ResponseMessage.builder()
                .header(ResponseMessageHeader.builder()
                        .result(true)
                        .resultCode("")
                        .message("")
                        .status(HttpStatus.OK.value())
                        .build())
                .body(data)
                .build();
    }
}
