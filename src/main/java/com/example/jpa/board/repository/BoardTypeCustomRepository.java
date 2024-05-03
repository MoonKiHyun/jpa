package com.example.jpa.board.repository;

import com.example.jpa.board.model.BoardTypeCount;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Repository
public class BoardTypeCustomRepository {

    private final EntityManager entityManager;

    public List<BoardTypeCount> getBoardTypeCount() {

        String sql = "select bt.id, bt.board_name, bt.created_at, bt.using_yn" +
                ", (select count(*) from board b where b.board_type_id = bt.id) as board_count " +
                "from board_type bt";

        List<Object[]> result = entityManager.createNativeQuery(sql).getResultList();

        return result.stream().map(BoardTypeCount::new).collect(Collectors.toList());
    }
}
