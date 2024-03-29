package com.example.jpa.user.service;

import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.UserLogCount;
import com.example.jpa.user.model.UserNoticeCount;
import com.example.jpa.user.model.UserStatus;
import com.example.jpa.user.model.UserSummary;
import com.example.jpa.user.repository.UserCustomRepository;
import com.example.jpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;

    @Override
    public UserSummary getUserStatusCount() {
        long stopUserCount = userRepository.countByStatus(UserStatus.STOP);
        long usingUserCount = userRepository.countByStatus(UserStatus.USING);
        long totalUserCount = userRepository.count();

        return UserSummary.builder()
                .stopUserCount(stopUserCount)
                .usingUserCount(usingUserCount)
                .totalUserCount(totalUserCount)
                .build();
    }

    @Override
    public List<User> getTodayUserList() {

        LocalDateTime t = LocalDateTime.now();

        LocalDateTime startDate = LocalDateTime.of(t.getYear(), t.getMonth(), t.getDayOfMonth(), 0, 0);
        LocalDateTime endDate = startDate.plusDays(1);

        return userRepository.findToday(startDate, endDate);
    }

    @Override
    public List<UserNoticeCount> getUserNoticeCount() {
        return userCustomRepository.findUserNoticeCount();
    }

    @Override
    public List<UserLogCount> getUserLogCount() {
        return userCustomRepository.findUserLogCount();
    }

    @Override
    public List<UserLogCount> findUserLikeBest() {
        return userCustomRepository.findUserLikeBest();
    }
}
