package com.example.jpa.notice.model;

import lombok.Getter;

import java.util.List;

@Getter
public class NoticeDeleteInput {

    private List<Long> idList;
}
