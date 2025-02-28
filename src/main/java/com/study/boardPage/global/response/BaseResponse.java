package com.study.boardPage.global.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public record BaseResponse<T>(
        @JsonIgnore
        HttpStatus httpStatus, Boolean isSuccess, String message, int code, T data) {
    // 성공했을때 단순한 성공 응답을 반환
    public static BaseResponse<Void> ok() {
        return new BaseResponse<>(
                SuccessCode.SUCCESS.getHttpStatus(),
                SuccessCode.SUCCESS.getIsSuccess(),
                SuccessCode.SUCCESS.getMessage(),
                SuccessCode.SUCCESS.getCode(),
                null
        );
    }

    // 성공했을때 -> 응답값 데이터 있을때
    public static <T> BaseResponse<T> ok(SuccessCode code, T data) {
        return new BaseResponse<>(
                code.getHttpStatus(),
                code.getIsSuccess(),
                code.getMessage(),
                code.getCode(),
                data
        );
    }


    // 성공했을때 -> 응답값 데이터 없을때
    public static BaseResponse<Void> ok(SuccessCode code) {
        return new BaseResponse<>(
                code.getHttpStatus(),
                code.getIsSuccess(),
                code.getMessage(),
                code.getCode(),
                null
        );
    }
    // 실패했을때 -> 공통된 에러메세지 일관되게 사용
    public static BaseResponse<Void> fail(ErrorCode code) {
        return new BaseResponse<>(
                code.getHttpStatus(),
                code.getIsSuccess(),
                code.getMessage(),
                code.getCode(),
                null
        );
    }
    // 실패했을때 -> 특정 상황에 맞게 메세지를 커스터마이징
    public static BaseResponse<Void> fail(ErrorCode code, String message) {
        return new BaseResponse<>(
                code.getHttpStatus(),
                code.getIsSuccess(),
                message,
                code.getCode(),
                null
        );
    }
}
