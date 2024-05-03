package com.example.jpa.board.service;

import com.example.jpa.board.entity.*;
import com.example.jpa.board.model.*;
import com.example.jpa.board.repository.*;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardHitsRepository boardHitsRepository;
    private final BoardTypeRepository boardTypeRepository;
    private final BoardBadReportRepository boardBadReportRepository;
    private final BoardTypeCustomRepository boardTypeCustomRepository;

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

    @Override
    public List<BoardTypeCount> getBoardTypeCount() {

        return boardTypeCustomRepository.getBoardTypeCount();
    }

    @Override
    public ServiceResult setBoardTop(Long id, boolean topYn) {

        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 존재하지 않습니다.");
        }

        Board board = optionalBoard.get();
        if (board.isTopYn() == topYn) {
            if (topYn) {
                return ServiceResult.fail("이미 게시글이 최상단에 있습니다.");
            } else {
                return ServiceResult.fail("이미 게시글이 최상단 배치가 해제되어 있습니다.");
            }
        }

        board.setTopYn(topYn);
        boardRepository.save(board);

        return ServiceResult.success();
    }

    @Override
    public ServiceResult setBoardPeriod(Long id, BoardPeriod boardPeriod) {

        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 존재하지 않습니다.");
        }

        Board board = optionalBoard.get();
        board.setPublishStartDate(boardPeriod.getStartDate());
        board.setPublishEndDate(boardPeriod.getEndDate());
        boardRepository.save(board);

        return ServiceResult.success();
    }

    @Override
    public ServiceResult setBoardHits(Long id, String email) {

        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 존재하지 않습니다.");
        }
        Board board = optionalBoard.get();

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
        }
        User user = optionalUser.get();

        if (boardHitsRepository.countByBoardAndUser(board, user) > 0) {
            return ServiceResult.fail("이미 조회수가 있습니다.");
        }

        boardHitsRepository.save(BoardHits.builder()
                .board(board)
                .user(user)
                .build());

        return ServiceResult.success();
    }

    @Override
    public ServiceResult setBoardLike(Long id, String email) {

        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 존재하지 않습니다.");
        }
        Board board = optionalBoard.get();

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
        }
        User user = optionalUser.get();

        long boardLikeCount = boardLikeRepository.countByBoardAndUser(board, user);
        if (boardLikeCount > 0) {
            return ServiceResult.fail("이미 좋아요한 내용이 있습니다.");
        }

        boardLikeRepository.save(BoardLike.builder()
                .board(board)
                .user(user)
                .build());

        return ServiceResult.success();
    }

    @Override
    public ServiceResult setBoardUnlike(Long id, String email) {

        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 존재하지 않습니다.");
        }
        Board board = optionalBoard.get();

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
        }
        User user = optionalUser.get();

        Optional<BoardLike> optionalBoardLike = boardLikeRepository.findByBoardAndUser(board, user);

        if (optionalBoardLike.isEmpty()) {
            return ServiceResult.fail("좋아요한 내용이 없습니다.");
        }

        BoardLike boardLike = optionalBoardLike.get();
        boardLikeRepository.delete(boardLike);

        return ServiceResult.success();
    }

    @Override
    public ServiceResult addBadReport(Long id, String email, BoardBadReportInput boardBadReportInput) {

        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return ServiceResult.fail("게시글이 존재하지 않습니다.");
        }
        Board board = optionalBoard.get();

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return ServiceResult.fail("회원 정보가 존재하지 않습니다.");
        }
        User user = optionalUser.get();

        BoardBadReport boardBadReport = BoardBadReport.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .userEmail(user.getEmail())
                .boardId(board.getId())
                .boardUserId(board.getUser().getId())
                .boardTitle(board.getTitle())
                .boardContents(board.getContent())
                .boardRegDate(board.getCreatedAt())
                .comment(boardBadReportInput.getComment())
                .build();

        boardBadReportRepository.save(boardBadReport);

        return ServiceResult.success();
    }

    @Override
    public List<BoardBadReport> badReportList() {

        return boardBadReportRepository.findAll();
    }
}
