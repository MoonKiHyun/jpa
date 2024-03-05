package com.example.jpa.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInputFind {

    @NotBlank(message = "필수 입력 항목입니다.")
    private String username;

    @NotBlank(message = "필수 입력 항목입니다.")
    private String phoneNumber;
}
