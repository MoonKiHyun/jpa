package com.example.jpa.user.controller;

import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.exception.UserNotFoundException;
import com.example.jpa.user.model.UserInput;
import com.example.jpa.user.model.UserUpdate;
import com.example.jpa.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@RestController
public class ApiUserController {

    private final UserRepository userRepository;

    @PostMapping("/api/user")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInput userInput, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors.hasErrors()) {

            errors.getAllErrors().forEach(error -> {
                responseErrorList.add(ResponseError.of((FieldError) error));
            });

            return ResponseEntity.ok().body(responseErrorList);
        }

        User user = User.builder()
                .email(userInput.getEmail())
                .username(userInput.getUsername())
                .password(userInput.getPassword())
                .phoneNumber(userInput.getPhoneNumber())
                .build();

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdate userUpdate, Errors errors) {

        List<ResponseError> responseErrorList = new ArrayList<>();

        if (errors.hasErrors()) {

            errors.getAllErrors().forEach(error -> {
                responseErrorList.add(ResponseError.of((FieldError) error));
            });

            return ResponseEntity.ok().body(responseErrorList);
        }

        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("사용자 정보가 없습니다."));

        user.setPhoneNumber(userUpdate.getPhoneNumber());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
