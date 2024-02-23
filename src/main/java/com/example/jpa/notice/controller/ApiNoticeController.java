package com.example.jpa.notice.controller;

import com.example.jpa.notice.NoticeRepository;
import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.exception.NoticeNotFoundException;
import com.example.jpa.notice.model.NoticeInput;
import com.example.jpa.notice.model.NoticeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                .hits(0)
                .likes(0)
                .build();

        noticeRepository.save(notice);

        return notice;
    }

    @GetMapping("/api/notice/{id}")
    public Notice notice(@PathVariable Long id) {

        return noticeRepository.findById(id).orElseThrow(null);
    }

    @ExceptionHandler(NoticeNotFoundException.class)
    public ResponseEntity<String> handlerNoticeNotFoundException(NoticeNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/notice/{id}")
    public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {

        Notice notice = noticeRepository.findById(id).orElseThrow(() ->
                new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        notice.setTitle(noticeInput.getTitle());
        notice.setContent(noticeInput.getContent());
        noticeRepository.save(notice);
    }

    @PatchMapping("/api/notice/{id}/hits")
    public void noticeHits(@PathVariable Long id) {

        Notice notice = noticeRepository.findById(id).orElseThrow(() ->
                new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        notice.setHits(notice.getHits() + 1);

        noticeRepository.save(notice);
    }
}
