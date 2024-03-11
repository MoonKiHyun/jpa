package com.example.jpa.user.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNoticeCount {

    private Long id;
    private String email;
    private String username;
    private Long noticeCount;
}
