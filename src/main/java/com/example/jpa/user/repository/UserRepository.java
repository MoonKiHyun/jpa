package com.example.jpa.user.repository;

import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    int countByEmail(String email);

    Optional<User> findByIdAndPassword(Long id, String password);

    Optional<User> findByUsernameAndPhoneNumber(String username, String phoneNumber);

    List<User> findByEmailContainsOrPhoneNumberContainsOrUsernameContains(String email, String username, String phoneNumber);

    long countByStatus(UserStatus userStatus);

    @Query("select u from User u where u.createdAt between :startDate and :endDate")
    List<User> findToday(LocalDateTime startDate, LocalDateTime endDate);
}
