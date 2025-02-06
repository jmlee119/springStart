package com.study.boardPage.board.presentation;

import com.study.boardPage.board.application.BoardService;
import com.study.boardPage.board.domain.Board;
import com.study.boardPage.board.dto.req.BoardCreateDto;
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
    private static final String CACHE_PREFIX = "board:";
    private RedisTemplate<String,Board> redisTemplate;
    public BoardController(BoardService boardService, BoardRepository boardRepository, RedisTemplate<String,Board> redisTemplate) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
        this.redisTemplate = redisTemplate;
    }
    @GetMapping("/board/{id}")
    public ResponseEntity<ApiResponse<BoardReadDto>> getBoardID(@PathVariable(name="id") int id) {
        BoardReadDto board = boardService.getBoardById(id);
        if (board != null) {
            return ResponseEntity.ok(ApiResponse.success(board));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail("게시글을 찾을 수 없습니다",404));
    }

//    @GetMapping("/board/{id}")
//    public Board getBoardId(@PathVariable(name="id") int id) {
//        String channelKey = CACHE_PREFIX + id;
//
//        //1. redis 에서 캐시된 데이터 조회
//        Board chahedBoard = redisTemplate.opsForValue().get(channelKey);
//        if (chahedBoard != null) {
//            System.out.println("return 중");
//            return chahedBoard;
//        }
//        // 2. 캐시된 데이터가 없으면 db에서 조회
//        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("찾을 수 없음"));
//
//        // 3. 조회한 데이터를 redis 에 캐싱 (TTL : 10분)
//        redisTemplate.opsForValue().set(channelKey, board, 10, TimeUnit.MINUTES);
//        return board;
//
//    }

    @GetMapping("/board")
    public ResponseEntity<BoardAllDto> getBoardAll() {
        BoardAllDto board = boardService.getAllBoards();
        if (board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/board")
    public ResponseEntity<BoardCreateDto> addBoard(@RequestBody BoardCreateDto boardCreateDto) {
        boardService.addBoard(boardCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
