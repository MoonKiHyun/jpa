package com.example.jpa.notice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime regDate;

    @Column
    private LocalDateTime UpdateDate;

    @Column
    private int hits;

    @Column
    private int likes;
}
