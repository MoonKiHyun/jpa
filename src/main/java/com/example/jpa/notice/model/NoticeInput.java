package com.example.jpa.notice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoticeInput {

    private String title;
    private String content;
}
