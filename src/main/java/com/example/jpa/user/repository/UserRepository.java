package com.example.jpa.user.repository;

import com.example.jpa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);
    int countByEmail(String email);

    Optional<User> findByIdAndPassword(Long id, String password);

    Optional<User> findByUsernameAndPhoneNumber(String username, String phoneNumber);
}
