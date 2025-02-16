package com.study.boardPage.global.exception;

import com.study.boardPage.global.response.BaseResponse;
import com.study.boardPage.global.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.BindException;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
    // 사용자가 정의한 baseException 이 발생하면 해당메서드가 실행
    // response 객체를 생성하고 예외의 상태 코드와 메세지를 설정
    // ResponseEntity 를 사용하여 클라이언트에게 http 응답을 보냄
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Void>> baseException(BaseException e) {
        BaseResponse<Void> response = BaseResponse.fail(e.getErrorCode());
        log.error("사용자가 정의한 baseException");
        return ResponseEntity.status(response.httpStatus()).body(response);
    }

    // DTO 유효성 검사 실패
    // ErrorCode.VALIDATION_FAIL_ERROR  활용해 에러 응답을 생성하고 반환
    @ExceptionHandler(BindException.class)
    public ResponseEntity<BaseResponse<Void>> validationException(BindException e) {
        BaseResponse<Void> response = new BaseResponse<>(
                ErrorCode.VALIDATION_FAIL_ERROR.getHttpStatus(),
                ErrorCode.VALIDATION_FAIL_ERROR.getIsSuccess(),
                e.getMessage(),
                ErrorCode.VALIDATION_FAIL_ERROR.getCode(),
                null);
        log.error("DTO 유효성 검사 실패");
        return new ResponseEntity<>(response, response.httpStatus());
    }
    // HTTP 요청 메서드 미지원
    // ex) post 로 요청해야하는 api에 get 요청을 보내면 발생함
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<BaseResponse<Void>> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        BaseResponse<Void> response = new BaseResponse<>(
                ErrorCode.NOT_SUPPORTED_METHOD.getHttpStatus(),
                ErrorCode.NOT_SUPPORTED_METHOD.getIsSuccess(),
                e.getMessage(),
                ErrorCode.NOT_FOUND_URL.getCode(),
                null
        );
        log.error("HTTP 요청 메서드 미지원");
        return new ResponseEntity<>(response, ErrorCode.NOT_SUPPORTED_METHOD.getHttpStatus());
    }
    // 404 notFound 예외 처리
    // 존재하지 않는 url 에 접근할 경우 발생함
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<BaseResponse<Void>> noResourceFoundException(NoResourceFoundException e) {
        BaseResponse<Void> response = new BaseResponse<>(
                ErrorCode.NOT_FOUND_URL.getHttpStatus(),
                ErrorCode.NOT_FOUND_URL.getIsSuccess(),
                ErrorCode.NOT_FOUND_URL.getMessage(),
                ErrorCode.NOT_FOUND_URL.getCode(),
                null
        );
        log.error("404 notFound 예외 처리");
        return new ResponseEntity<>(response, ErrorCode.NOT_FOUND_URL.getHttpStatus());
    }
    // 핸들링 되지 않은 예외처리
    // 위에서 정의하지 않은 모든 예외들을 처리함
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> notHandlerException(Exception e) {
        BaseResponse<Void> reponse = new BaseResponse<>(
                ErrorCode.FAIL.getHttpStatus(),
                ErrorCode.FAIL.getIsSuccess(),
                ErrorCode.FAIL.getMessage(),
                ErrorCode.FAIL.getCode(),
                null
        );
        log.error("핸들링 되지 않은 예외처리");
        return new ResponseEntity<>(reponse, ErrorCode.FAIL.getHttpStatus());
    }
    // 데이터 무결성 위반 예외처리
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BaseResponse<Void>> dataIntegrityViolationException(DataIntegrityViolationException e) {
        BaseResponse<Void> response = new BaseResponse<>(
                ErrorCode.INVALID_REQUEST_DATA.getHttpStatus(),
                ErrorCode.NOT_FOUND_URL.getIsSuccess(),
                ErrorCode.INVALID_REQUEST_DATA.getMessage(),
                ErrorCode.BOARD_READ_FAILED.getCode(),
                null
        );
        log.error("데이터 무결성 위반 예외처리");
        return new ResponseEntity<>(response, ErrorCode.INVALID_REQUEST_DATA.getHttpStatus());
    }
}
