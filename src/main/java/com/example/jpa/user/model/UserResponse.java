package com.example.jpa.user.model;

import com.example.jpa.user.entity.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

    private Long id;
    private String email;
    private String username;
    private String phoneNumber;

    /*
    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.phoneNumber = user.getPhoneNumber();
    }
    */

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
