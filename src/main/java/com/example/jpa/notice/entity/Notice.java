package com.example.jpa.notice.entity;

import com.example.jpa.global.TimeStamp;
import com.example.jpa.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
public class Notice extends TimeStamp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int hits;

    @Column
    private int likes;

    @Column
    private boolean deleted;

    @Column
    private LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
