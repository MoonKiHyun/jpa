package com.example.jpa.notice.controller;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.exception.AlreadyDeletedException;
import com.example.jpa.notice.exception.DuplicateNoticeException;
import com.example.jpa.notice.exception.NoticeNotFoundException;
import com.example.jpa.notice.model.NoticeDeleteInput;
import com.example.jpa.notice.model.NoticeInput;
import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.notice.repository.NoticeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    /*
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
    */

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

    /*
    @DeleteMapping("api/notice/{id}")
    public void deleteNotice(@PathVariable Long id) {

        Notice notice = noticeRepository.findById(id).orElseThrow(() ->
                new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        noticeRepository.delete(notice);
    }
    */

    @ExceptionHandler(NoticeNotFoundException.class)
    public ResponseEntity<String> handlerAlreadyDeletedException(AlreadyDeletedException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
    }

    @DeleteMapping("api/notice/{id}")
    public void deleteNotice(@PathVariable Long id) {

        Notice notice = noticeRepository.findById(id).orElseThrow(() ->
                new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        if (notice.isDeleted()) {
            throw new AlreadyDeletedException("이미 삭제된 공지사항입니다.");
        }

        notice.setDeleted(true);
        notice.setDeletedDate(LocalDateTime.now());

        noticeRepository.save(notice);
    }

    @DeleteMapping("/api/notice")
    public void deleteNoticeList(@RequestBody NoticeDeleteInput noticeDeleteInput) {

         List<Notice> noticeList = noticeRepository.findByIdIn(noticeDeleteInput.getIdList()).orElseThrow(() ->
                 new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

         noticeList.forEach(notice -> {
             notice.setDeleted(true);
             notice.setDeletedDate(LocalDateTime.now());
         });

         noticeRepository.saveAll(noticeList);
    }

    @DeleteMapping("/api/notice/all")
    public void deleteAllNotice() {

        noticeRepository.deleteAll();
    }

    /*
    @PostMapping("/api/notice")
    public void addNotice(@RequestBody NoticeInput noticeInput) {

        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .content(noticeInput.getContent())
                .hits(0)
                .likes(0)
                .build();

        noticeRepository.save(notice);
    }
    */

    /*
    @PostMapping("/api/notice")
    public ResponseEntity<List<ResponseError>> addNotice(@RequestBody @Valid NoticeInput noticeInput, Errors errors) {

        if (errors.hasErrors()) {

            List<ResponseError> responseErrors = new ArrayList<>();

            errors.getAllErrors().forEach(error -> {
                responseErrors.add(ResponseError.of((FieldError) error));
            });

            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }

        noticeRepository.save(Notice.builder()
                .title(noticeInput.getTitle())
                .content(noticeInput.getContent())
                .hits(0)
                .likes(0)
                .build());

        return ResponseEntity.ok().build();
    }
    */

    @GetMapping("/api/notice/latest/{size}")
    public Page<Notice> noticeLatest(@PathVariable int size) {

        return noticeRepository.findAll(PageRequest.of(0, size, Sort.Direction.DESC, "createdAt"));
    }

    @ExceptionHandler(DuplicateNoticeException.class)
    public ResponseEntity<String> handlerDuplicateNoticeException(DuplicateNoticeException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/notice")
    public void addNotice(@RequestBody NoticeInput noticeInput) {

        LocalDateTime checkDate = LocalDateTime.now().minusMinutes(1);

        int noticeCount = noticeRepository.countByTitleAndContentAndCreatedAtIsGreaterThanEqual(noticeInput.getTitle(), noticeInput.getContent(), checkDate);

        if (noticeCount > 0) {
            throw new DuplicateNoticeException("1분 이내에 등록된 동일한 공지사항이 존재합니다.");
        }

        noticeRepository.save(Notice.builder()
                .title(noticeInput.getTitle())
                .content(noticeInput.getContent())
                .hits(0)
                .likes(0)
                .build());
    }

}
