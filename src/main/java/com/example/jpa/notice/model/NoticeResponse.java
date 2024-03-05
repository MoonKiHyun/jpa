package com.example.jpa.notice.model;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeResponse {

    private Long id;
    private Long regUserId;
    private String regUsername;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int hits;
    private int likes;

    public static NoticeResponse of(Notice notice) {

        return NoticeResponse.builder()
                .id(notice.getId())
                .regUserId(notice.getUser().getId())
                .regUsername(notice.getUser().getUsername())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .modifiedAt(notice.getModifiedAt())
                .hits(notice.getHits())
                .likes(notice.getLikes())
                .build();
    }
}
