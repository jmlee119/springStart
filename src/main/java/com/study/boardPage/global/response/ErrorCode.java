package com.study.boardPage.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 1000 ~ 1999 인증, 인가 에러

    // 2000 ~ 2999 회원 서비스
    USER_EMAIL_EXIST_FAILED(HttpStatus.BAD_REQUEST, Boolean.FALSE, 2001, "이미 존재하는 이메일입니다"),
    USER_NICKNAME_EXIST_FAILED(HttpStatus.BAD_REQUEST, Boolean.FALSE, 2002, "이미 존재하는 닉네임입니다"),
    // 3000 ~ 3999 게시판 서비스
    BOARD_CREATE_FAILED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3001, "글 작성 실패"),
    BOARD_READ_FAILED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3002, "글 조회 불가"),
    BOARD_READALL_FAILED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3003, "글 조회 불가"),
    BOARD_UPDATE_FAILED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3004, "글 수정 실패"),
    BOARD_DELETE_FAILED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3005, "글 삭제 실패"),
    BOARD_TYPE_UNDEFINED(HttpStatus.BAD_REQUEST,Boolean.FALSE, 3006, "게시판 유형을 확인"),
    //9000 ~ 9999
    //오류 종류 : 공통 에러
    VALIDATION_FAIL_ERROR(HttpStatus.BAD_REQUEST, Boolean.FALSE, 9000, "(exception error 메세지에 따름)"),
    NOT_SUPPORTED_METHOD(HttpStatus.METHOD_NOT_ALLOWED, Boolean.FALSE, 9001, "(exception error 메세지에 따름"),
    NOT_FOUND_URL(HttpStatus.NOT_FOUND, Boolean.FALSE, 9002, "요청하신 URL 을 찾을 수 없습니다."),
    INVALID_REQUEST_DATA(HttpStatus.BAD_REQUEST, Boolean.FALSE, 9003, "데이터 저장 실패, 재시도 혹은 관리자에게 문의해주세요."),
    FAIL(HttpStatus.BAD_REQUEST, Boolean.FALSE, 9999, "요청 응답 실패, 관리자에게 문의해주세요."),
            ;
    private final HttpStatus httpStatus;
    private final Boolean isSuccess;
    private final Integer code;
    private final String message;
}
