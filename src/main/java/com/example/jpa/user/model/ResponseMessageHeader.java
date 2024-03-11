package com.example.jpa.user.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMessageHeader {

    private Boolean result;
    private String resultCode;
    private String message;
    private int status;
}
