package com.study.boardPage.board.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardReadDto {
    private int boardType;
    private int id;
    private String title;
    private String content;
    private LocalDateTime rgdt;

}
