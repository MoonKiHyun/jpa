package com.example.jpa.user.repository;

import com.example.jpa.user.model.UserLogCount;
import com.example.jpa.user.model.UserNoticeCount;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor

@Repository
public class UserCustomRepository {

    private final EntityManager entityManager;

    public List<UserNoticeCount> findUserNoticeCount() {

        String sql = "SELECT u.id, u.email, u.username, (SELECT COUNT(*) FROM notice n WHERE n.user_id = u.id) AS notice_count FROM user u";

        List<UserNoticeCount> list = entityManager.createNativeQuery(sql).getResultList();

        return list;
    }

    public List<UserLogCount> findUserLogCount() {

        String sql = "SELECT u.id, u.email, u.username, " +
                "(SELECT COUNT(*) FROM notice n WHERE n.user_id = u.id) AS notice_count, " +
                "(SELECT COUNT(*) FROM notice_like nl WHERE nl.user_id = u.id) AS notice_like_count " +
                "FROM user u";

        List<UserLogCount> list = entityManager.createNativeQuery(sql).getResultList();

        return list;
    }

    public List<UserLogCount> findUserLikeBest() {

        String sql = "SELECT t1.id, t1.email, t1.username, t1.notice_like_count FROM (SELECT u.*, (SELECT COUNT(*) FROM notice_like nl WHERE nl.user_id = u.id) AS notice_like_count FROM user u) t1 ORDER BY t1.notice_like_count DESC";

        List<UserLogCount> list = entityManager.createNativeQuery(sql).getResultList();

        return list;
    }
}
