package com.study.boardPage.global.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    // generic : 클래스나 매서드 형태를 고정하지 않고 사용할 때 원하는 대로 지정할 수 있음
    private final boolean isSuccess;
    private final String message;
    private final int code;
    private final T data;
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "요청 성공", 200, data);
    }
    public static <T> ApiResponse<T> fail (String message, int code){
        return new ApiResponse<>(false, message, code, null);

    }
}
//{
//        "isSuccess": true,
//        "message": "요청에 성공하였습니다.",
//        "code": 200,
//        "result": {
//        "":""
//        }
//        }