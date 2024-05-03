package com.example.jpa.board.entity;

import com.example.jpa.global.TimeStamp;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
public class BoardBadReport extends TimeStamp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 신고자 정보
    @Column
    private Long userId;

    @Column
    private String username;

    @Column
    private String userEmail;

    // 신고 게시글 정보
    @Column
    private long boardUserId;

    @Column
    private Long boardId;

    @Column
    private String boardTitle;

    @Column
    private String boardContents;

    @Column
    private LocalDateTime boardRegDate;

    // 신고 내용
    @Column
    private String comment;
}
