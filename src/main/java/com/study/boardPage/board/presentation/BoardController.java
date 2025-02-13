package com.study.boardPage.board.presentation;

import com.study.boardPage.board.application.BoardService;
import com.study.boardPage.board.domain.Board;
import com.study.boardPage.board.dto.req.BoardCreateDto;
import com.study.boardPage.board.dto.req.BoradUpdateDto;
import com.study.boardPage.board.dto.resp.BoardAllDto;
import com.study.boardPage.board.dto.resp.BoardReadDto;
import com.study.boardPage.board.infrastructure.BoardRepository;
import com.study.boardPage.global.response.dto.ApiResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.TimeUnit;

@RestController // controller는 view 를 반환 restcontroller data 반환
@RequestMapping("/api/v1")
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    public BoardController(BoardService boardService, BoardRepository boardRepository) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
    }
    @GetMapping("/board/{id}")
    public ResponseEntity<ApiResponse<BoardReadDto>> getBoardID(@PathVariable(name="id") int id) {
        BoardReadDto board = boardService.getBoardById(id);
        if (board != null) {
            return ResponseEntity.ok(ApiResponse.success(board));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail("게시글을 찾을 수 없습니다",404));
    }

    @GetMapping("/board")
    public ResponseEntity<ApiResponse<BoardAllDto>> getBoardAll() {
        BoardAllDto board = boardService.getAllBoards();
        if (board != null) {
            return ResponseEntity.ok(ApiResponse.success(board));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail("작성 글이 없습니다.",404));
    }
    @PostMapping("/board")
    public ResponseEntity<ApiResponse<BoardCreateDto>> addBoard(@RequestBody BoardCreateDto boardCreateDto) {
        boardService.addBoard(boardCreateDto);

        return ResponseEntity.ok(ApiResponse.success(boardCreateDto));
    }
    @PatchMapping("/board")
    public ResponseEntity<ApiResponse<BoradUpdateDto>> updateBoard(@RequestBody BoradUpdateDto boradUpdateDto) {
        boardService.updateBoard(boradUpdateDto);
        return ResponseEntity.ok(ApiResponse.success(boradUpdateDto));
    }
}
