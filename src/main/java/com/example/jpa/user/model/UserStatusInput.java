package com.example.jpa.user.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserStatusInput {

     private UserStatus status;
}
