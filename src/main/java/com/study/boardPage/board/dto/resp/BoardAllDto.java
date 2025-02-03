package com.study.boardPage.board.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardAllDto {
    private List<BoardReadDto> boardList;
}
