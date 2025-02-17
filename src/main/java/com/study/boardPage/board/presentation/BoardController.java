package com.study.boardPage.board.presentation;

import com.study.boardPage.board.application.BoardService;
import com.study.boardPage.board.domain.Board;
import com.study.boardPage.board.dto.req.BoardCreateDto;
import com.study.boardPage.board.dto.req.BoradUpdateDto;
import com.study.boardPage.board.dto.resp.BoardAllDto;
import com.study.boardPage.board.dto.resp.BoardReadDto;
import com.study.boardPage.board.infrastructure.BoardRepository;
import com.study.boardPage.global.exception.BaseException;
import com.study.boardPage.global.response.BaseResponse;
import com.study.boardPage.global.response.SuccessCode;
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

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @GetMapping("/board/{id}")
    public BaseResponse<BoardReadDto> getBoardID(@PathVariable(name = "id") int id){
        BoardReadDto board = boardService.getBoardById(id);
        return BaseResponse.ok(SuccessCode.BOARD_READ_SUCCESS, board);
    }

    @GetMapping("/board")
    public BaseResponse<BoardAllDto> getBoardAll() {
        BoardAllDto board = boardService.getAllBoards();
        return BaseResponse.ok(SuccessCode.BOARD_READALL_SUCCESS, board);
    }
    @PostMapping("/board")
    public BaseResponse<BoardCreateDto> addBoard(@RequestBody BoardCreateDto boardCreateDto) {
        boardService.addBoard(boardCreateDto);
        return BaseResponse.ok(SuccessCode.BOARD_CREATE_SUCCESS, boardCreateDto);
    }
    @PatchMapping("/board")
    public BaseResponse<BoradUpdateDto> updateBoard(@RequestBody BoradUpdateDto boradUpdateDto) {
        boardService.updateBoard(boradUpdateDto);
        return BaseResponse.ok(SuccessCode.BOARD_UPDATE_SUCCESS, boradUpdateDto);
    }
}
