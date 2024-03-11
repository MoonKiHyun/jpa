package com.example.jpa.user.controller;

import com.example.jpa.notice.repository.NoticeRepository;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.exception.UserNotFoundException;
import com.example.jpa.user.model.ResponseMessage;
import com.example.jpa.user.model.UserSearch;
import com.example.jpa.user.model.UserStatusInput;
import com.example.jpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@RestController
public class ApiAdminUserController {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;

    /*
    @GetMapping("/api/admin/user")
    public ResponseMessage userList() {

        List<User> userList = userRepository.findAll();
        long totalUserCount = userRepository.count();

        return ResponseMessage.builder().totalUserCount(totalUserCount).userList(userList).build();

    }
    */

    @GetMapping("/api/admin/user/{id}")
    public ResponseEntity<?> userDetail(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ResponseMessage.success(user), HttpStatus.OK);
    }

    @GetMapping("/api/admin/user/search")
    public ResponseEntity<?> findUser(@RequestBody UserSearch userSearch) {
        List<User> userList = userRepository.findByEmailContainsOrPhoneNumberContainsOrUsernameContains(userSearch.getEmail(), userSearch.getUsername(), userSearch.getPhoneNumber());

        return new ResponseEntity<>(ResponseMessage.success(userList), HttpStatus.OK);
    }

    @PatchMapping("/api/admin/user/{id}/status")
    public ResponseEntity<?> userStatus(@PathVariable Long id, @RequestBody UserStatusInput userStatusInput) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();

        user.setStatus(userStatusInput.getStatus());
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(ResponseMessage.fail("사용자 정보가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
        }


        User user = optionalUser.get();

        if (noticeRepository.countByUser(user) > 0) {
            return new ResponseEntity<>(ResponseMessage.fail("사용자가 작성한 공지사항이 있습니다."), HttpStatus.BAD_REQUEST);
        }

        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
