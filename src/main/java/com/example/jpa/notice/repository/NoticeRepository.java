package com.example.jpa.notice.repository;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Optional<List<Notice>> findByIdIn(List<Long> idList);

    int countByTitleAndContentAndCreatedAtIsGreaterThanEqual(String title, String content, LocalDateTime checkDate);

    List<Notice> findByUser(User user);

    long countByUser(User user);
}
