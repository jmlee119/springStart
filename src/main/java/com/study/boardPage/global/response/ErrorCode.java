package com.study.boardPage.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 1000 ~ 1999 인증, 인가 에러

    // 2000 ~ 2999 회원 서비스

    // 3000 ~ 3999 게시판 서비스
    BOARD_CREATE_FAILED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3001, "글 작성 실패"),
    BOARD_READ_FAILED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3002, "글 조회 불가"),
    BOARD_READALL_FAILED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3003, "글 조회 불가"),
    BOARD_UPDATE_FAILED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3004, "글 수정 실패"),
    BOARD_DELETE_FAILED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3005, "글 삭제 실패"),
    BOARD_TYPE_UNDEFINED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3006, "게시판 유형을 확인");

    private final HttpStatus httpStatus;
    private final Boolean isSuccess;
    private final Integer code;
    private final String message;
}
