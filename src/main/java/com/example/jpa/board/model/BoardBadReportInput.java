package com.example.jpa.board.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardBadReportInput {

    private String comment;

}
