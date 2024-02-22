package com.example.jpa.notice.controller;

import com.example.jpa.notice.NoticeRepository;
import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.model.NoticeInput;
import com.example.jpa.notice.model.NoticeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiNoticeController {

    private final NoticeRepository noticeRepository;

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

    /*
    @GetMapping("/api/notice")
    public List<NoticeModel> noticeList() {

        List<NoticeModel> noticeList = new ArrayList<>();

        return noticeList;
    }

    @GetMapping("api/notice/count")
    public Long noticeCount() {

        return 10L;
    }
    */

    /*
    @PostMapping("/api/notice")
    public NoticeModel addNotice(@RequestBody NoticeModel noticeModel) {

        noticeModel.setId(1L);
        noticeModel.setRegDate(LocalDateTime.now());

        return noticeModel;
    }
    */

    /*
    @PostMapping("api/notice")
    public Notice addNotice(@RequestBody NoticeInput noticeInput) {

        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .content(noticeInput.getContent())
                .regDate(LocalDateTime.now())
                .build();

        noticeRepository.save(notice);

        return notice;
    }
    */

    @PostMapping("/api/notice")
    public Notice addNotice(@RequestBody NoticeInput noticeInput) {

        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .content(noticeInput.getContent())
                .regDate(LocalDateTime.now())
                .hits(0)
                .likes(0)
                .build();

        noticeRepository.save(notice);

        return notice;
    }
}
