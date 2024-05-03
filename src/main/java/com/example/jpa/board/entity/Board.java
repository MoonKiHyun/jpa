package com.example.jpa.board.entity;

import com.example.jpa.global.TimeStamp;
import com.example.jpa.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
public class Board extends TimeStamp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private boolean TopYn;

    @Column
    private LocalDate publishStartDate;

    @Column
    private LocalDate publishEndDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_type_id")
    private BoardType boardType;
}
