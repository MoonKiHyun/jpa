package com.example.jpa.user.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLogCount {

    private Long id;
    private String email;
    private String username;
    private Long noticeCount;
    private Long noticeLikeCount;
}
