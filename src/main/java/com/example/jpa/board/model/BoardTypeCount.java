package com.example.jpa.board.model;

import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardTypeCount {

    private long id;
    private String boardName;
    private LocalDateTime regDate;
    private boolean usingYn;
    private long boardCount;

    public BoardTypeCount(Object[] e) {
        this.id = (Long)e[0];
        this.boardName = (String) e[1];
        this.regDate = ((Timestamp) e[2]).toLocalDateTime();
        this.usingYn = (Boolean) e[3];
        this.boardCount = (Long)e[4];
    }
}
