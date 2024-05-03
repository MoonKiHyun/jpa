package com.example.jpa.board.model;

import lombok.*;

import java.time.LocalDate;
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPeriod {

    private LocalDate startDate;
    private LocalDate endDate;
}
