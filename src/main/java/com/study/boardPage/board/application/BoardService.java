package com.study.boardPage.board.application;

import com.study.boardPage.board.domain.Board;
import com.study.boardPage.board.dto.req.BoardCreateDto;
import com.study.boardPage.board.dto.req.BoradUpdateDto;
import com.study.boardPage.board.dto.resp.BoardAllDto;
import com.study.boardPage.board.dto.resp.BoardReadDto;
import com.study.boardPage.board.infrastructure.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Integer addBoard(BoardCreateDto boardCreateDto) {
        Board board = new Board();
        board.setTitle(boardCreateDto.getTitle());
        board.setContent(boardCreateDto.getContent());
        board.setBoardType(boardCreateDto.getBoardType());
        board.setRgdt(LocalDateTime.now());
        Board  saveboard = boardRepository.save(board);
        return saveboard.getId();
    }
    @Transactional
    // jpa 엔티티 변경 감지 기능이 작동하려면 트랜잭션 안에서 엔티티를 변경해야함 (update 시 필요)
    // 읽기전용 => transactional 필요없음
    public Integer updateBoard(BoradUpdateDto boradUpdateDto) {
        Board board = boardRepository.findById(boradUpdateDto.getBoardId()).orElseThrow(()->new IllegalArgumentException("글 찾을 수 없습니다."));
        board.update(boradUpdateDto.getTitle(), boradUpdateDto.getContent());
        return board.getId();
    }
}
