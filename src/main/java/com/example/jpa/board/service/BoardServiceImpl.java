package com.example.jpa.board.service;

import com.example.jpa.board.entity.Board;
import com.example.jpa.board.entity.BoardType;
import com.example.jpa.board.model.BoardTypeInput;
import com.example.jpa.board.model.BoardTypeUsing;
import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.board.repository.BoardRepository;
import com.example.jpa.board.repository.BoardTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardTypeRepository boardTypeRepository;

    @Override
    public ServiceResult addBoard(BoardTypeInput boardTypeInput) {

        BoardType boardType = boardTypeRepository.findByBoardName(boardTypeInput.getName());

        if (boardType != null && boardTypeInput.getName().equals(boardType.getBoardName())) {
            return ServiceResult.fail("이미 동일한 게시물이 존재합니다.");
        }

        BoardType addBoardType = BoardType.builder()
                .boardName(boardTypeInput.getName())
                .build();
        boardTypeRepository.save(addBoardType);

        return ServiceResult.success();
    }

    @Override
    public ServiceResult updateBoard(Long id, BoardTypeInput boardTypeInput) {

        Optional<BoardType> optionalBoardType = boardTypeRepository.findById(id);

        if (optionalBoardType.isEmpty()) {
            return ServiceResult.fail("수정할 게시판 타입이 없습니다.");
        }

        BoardType boardType = optionalBoardType.get();

        if (boardTypeInput.getName().equals(boardType.getBoardName())) {
            return ServiceResult.fail("수정할 이름이 동일한 게시물입니다.");
        }

        boardType.setBoardName(boardTypeInput.getName());
        boardTypeRepository.save(boardType);

        return ServiceResult.success();
    }

    @Override
    public ServiceResult deleteBoard(Long id) {

        Optional<BoardType> optionalBoardType = boardTypeRepository.findById(id);

        if (optionalBoardType.isEmpty()) {
            return ServiceResult.fail("삭제할 게시판 타입이 없습니다.");
        }

        BoardType boardType = optionalBoardType.get();

        if (boardRepository.countByBoardType(boardType) > 0) {
            return ServiceResult.fail("삭제할 게시판 타입의 게시글이 존재합니다.");
        }

        boardTypeRepository.delete(boardType);

        return ServiceResult.success();
    }

    @Override
    public List<BoardType> getAllBoardType() {

        return boardTypeRepository.findAll();
    }

    @Override
    public ServiceResult setBoardTypeUsing(Long id, BoardTypeUsing boardTypeUsing) {

        Optional<BoardType> optionalBoardType = boardTypeRepository.findById(id);

        if (optionalBoardType.isEmpty()) {
            return ServiceResult.fail("수정할 게시판 타입이 없습니다.");
        }

        BoardType boardType = optionalBoardType.get();

        boardType.setUsingYn(boardTypeUsing.isUsingYn());
        boardTypeRepository.save(boardType);

        return ServiceResult.success();
    }
}
