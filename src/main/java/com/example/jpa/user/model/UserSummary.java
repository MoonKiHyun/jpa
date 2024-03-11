package com.example.jpa.user.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSummary {

    private Long stopUserCount;
    private Long usingUserCount;
    private Long totalUserCount;
}
