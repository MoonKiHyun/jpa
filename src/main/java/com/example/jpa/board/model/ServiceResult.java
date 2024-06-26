package com.example.jpa.board.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ServiceResult {

    private boolean result;
    private String message;

    public static ServiceResult fail(String message) {

        return ServiceResult.builder()
                .result(false)
                .message(message)
                .build();
    }

    public static ServiceResult success() {

        return ServiceResult.builder()
                .result(true)
                .build();
    }

    public boolean isFail() {
        return !result;
    }
}
