package com.study.boardPage.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    // 회원

    // 게시판
    BOARD_CREATE_SUCCESS(HttpStatus.CREATED, Boolean.TRUE, 201, "게시글이 작성되었습니다."),
    BOARD_READ_SUCCESS(HttpStatus.OK, Boolean.TRUE, 200, "글을 읽을 수 있습니다"),
    BOARD_READALL_SUCCESS(HttpStatus.OK,Boolean.TRUE, 200, "글 전체 목록을 불러왔습니다"),
    BOARD_UPDATE_SUCCESS(HttpStatus.OK, Boolean.TRUE, 200, "글 수정이 완료 되었습니다"),
    BOARD_DELETE_SUCCESS(HttpStatus.OK,Boolean.TRUE, 200, "글 삭제 되었습니다."),

    // common
    SUCCESS(HttpStatus.OK, Boolean.TRUE, 200, "요청 응답 성공");
    private final HttpStatus httpStatus;
    private final Boolean isSuccess;
    private final Integer code;
    private final String message;
}
