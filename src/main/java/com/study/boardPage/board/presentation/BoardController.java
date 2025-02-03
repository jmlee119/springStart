package com.study.boardPage.board.presentation;

import com.study.boardPage.board.application.BoardService;
import com.study.boardPage.board.domain.Board;
import com.study.boardPage.board.dto.resp.BoardAllDto;
import com.study.boardPage.board.dto.resp.BoardReadDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // controller는 view 를 반환 restcontroller data 반환
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardReadDto> getBoardID(@PathVariable(name="id") int id) {
        BoardReadDto board = boardService.getBoardById(id);
        if (board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/list")
    public ResponseEntity<BoardAllDto> getBoardAll() {
        BoardAllDto board = boardService.getAllBoards();
        if (board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
