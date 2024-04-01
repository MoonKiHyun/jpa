package com.example.jpa.board.controller;

import com.example.jpa.board.entity.BoardType;
import com.example.jpa.board.model.BoardTypeInput;
import com.example.jpa.board.model.BoardTypeUsing;
import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.board.service.BoardService;
import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.user.model.ResponseMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
public class ApiBoardController {

    private final BoardService boardService;

    @PostMapping("/api/board/type")
    private ResponseEntity<?> addBoardType(@RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrorList = ResponseError.of(errors.getAllErrors());
            return ResponseEntity.badRequest().body(ResponseMessage.fail("입력 값이 정확하지 않습니다.", responseErrorList));
        }

        ServiceResult result = boardService.addBoard(boardTypeInput);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/board/type/{id}")
    public ResponseEntity<?> updateBoardType(@PathVariable Long id, @RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrorList = ResponseError.of(errors.getAllErrors());
            return ResponseEntity.badRequest().body(ResponseMessage.fail("입력 값이 정확하지 않습니다.", responseErrorList));
        }

        ServiceResult result = boardService.updateBoard(id, boardTypeInput);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/board/type/{id}")
    public ResponseEntity<?> deleteBoardType(@PathVariable Long id) {

        ServiceResult result = boardService.deleteBoard(id);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().body(ResponseMessage.success());
    }

    @GetMapping("/api/board/type")
    public ResponseEntity<?> boardType() {

        List<BoardType> boardTypeList = boardService.getAllBoardType();

        return ResponseEntity.ok().body(ResponseMessage.success(boardTypeList));
    }

    @PatchMapping("/api/board/type/{id}/using")
    public ResponseEntity<?> usingBoardType(@PathVariable Long id, @RequestBody BoardTypeUsing boardTypeUsing) {

        ServiceResult result = boardService.setBoardTypeUsing(id, boardTypeUsing);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().body(ResponseMessage.success());
    }
}
