package com.study.boardPage.board.application;

import com.study.boardPage.board.domain.Board;
import com.study.boardPage.board.dto.resp.BoardAllDto;
import com.study.boardPage.board.dto.resp.BoardReadDto;
import com.study.boardPage.board.infrastructure.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    public BoardReadDto getBoardById(int id) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board != null) {
            return new BoardReadDto(board.getId(), board.getBoardType(), board.getTitle(), board.getContent(), board.getRgdt());
        }
        return null;
    }

    public BoardAllDto getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        List<BoardReadDto> boardList = boards.stream()
                .map(board -> new BoardReadDto(
                        board.getBoardType(),
                        board.getId(),
                        board.getTitle(),
                        board.getContent(),
                        board.getRgdt()
                ))
                .collect(Collectors.toList());
        return new BoardAllDto(boardList);
    }
}
