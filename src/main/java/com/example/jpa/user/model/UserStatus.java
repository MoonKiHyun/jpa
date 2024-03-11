package com.example.jpa.user.model;

public enum UserStatus {
    NONE, USING, STOP;

    int value;

    UserStatus() {

    }

    public int getValue() {
        return value;
    }
}
