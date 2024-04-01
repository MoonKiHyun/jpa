package com.example.jpa.board.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardTypeInput {

    @NotBlank(message = "게시판 제목은 필수 항목입니다.")
    private String name;
}
