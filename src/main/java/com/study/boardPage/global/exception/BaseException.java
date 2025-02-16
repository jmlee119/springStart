package com.study.boardPage.global.exception;

import com.study.boardPage.global.response.ErrorCode;
import com.study.boardPage.global.response.dto.ResponseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseException extends RuntimeException {
    private final ErrorCode errorCode;
}
