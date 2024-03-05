package com.example.jpa.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInputPassword {

    @NotBlank(message = "현재 비밀번호는 필수 항목입니다.")
    private String password;

    @Size(min = 4, max = 20, message = "신규 비밀번호는 4-20자 사이의 길이로 입력해 주세요.")
    @NotBlank(message = "신규 비밀번호는 필수 항목입니다.")
    private String newPassword;
}
