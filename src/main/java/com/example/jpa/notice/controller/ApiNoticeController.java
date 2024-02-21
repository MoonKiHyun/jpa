package com.example.jpa.notice.controller;

import com.example.jpa.notice.model.NoticeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiNoticeController {

    /*
    @GetMapping("/api/notice")
    public NoticeModel notice() {

        return NoticeModel.builder()
                .id(1L)
                .title("공지사항입니다.")
                .content("공지사항 내용입니다.")
                .regDate(LocalDateTime.now())
                .build();
    }
    */

    /*
    @GetMapping("/api/notice")
    public List<NoticeModel> noticeList() {

        List<NoticeModel> noticeList = new ArrayList<>();

        NoticeModel noticeModel01 =  NoticeModel.builder()
                .id(1L)
                .title("공지사항01입니다.")
                .content("공지사항01 내용입니다.")
                .regDate(LocalDateTime.now())
                .build();

        NoticeModel noticeModel02 =  NoticeModel.builder()
                .id(2L)
                .title("공지사항02입니다.")
                .content("공지사항02 내용입니다.")
                .regDate(LocalDateTime.now())
                .build();

        noticeList.add(noticeModel01);
        noticeList.add(noticeModel02);

        return noticeList;
    }
    */

    @GetMapping("/api/notice")
    public List<NoticeModel> noticeList() {

        List<NoticeModel> noticeList = new ArrayList<>();

        return noticeList;
    }

    @GetMapping("api/notice/count")
    public Long noticeCount() {

        return 10L;
    }
}
