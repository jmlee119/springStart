package com.study.boardPage.global.response.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {
    // 성공
    SUCCESS(true, 200, "요청에 성공했습니다"),
    // 900 기타에러
    INTERNAL_SERVER_ERROR(false, 900, "Internal server error");
    // 게시판

    private final boolean isSuccess;
    private final int code;
    private final String message;

}
