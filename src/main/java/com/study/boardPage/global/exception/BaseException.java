package com.study.boardPage.global.exception;

import com.study.boardPage.global.response.dto.ResponseStatus;
import lombok.Getter;

@Getter
public class BaseException {
    private final ResponseStatus status;
    public BaseException(final ResponseStatus status) {
        this.status = status;
    }

}
