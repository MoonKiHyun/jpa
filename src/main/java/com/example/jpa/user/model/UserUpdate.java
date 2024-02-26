package com.example.jpa.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUpdate {

    @Size(max = 20, message = "연락처는 최대 20자 까지 입력해야 합니다.")
    @NotBlank(message = "전화번호는 필수 항목입니다.")
    private String phoneNumber;
}
