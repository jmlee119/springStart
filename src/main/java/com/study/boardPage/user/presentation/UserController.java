package com.study.boardPage.user.presentation;

import com.study.boardPage.global.response.BaseResponse;
import com.study.boardPage.global.response.SuccessCode;
import com.study.boardPage.user.application.UserService;
import com.study.boardPage.user.dto.SignupDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public BaseResponse<SignupDto> signup(@RequestBody SignupDto signupDto) {
        userService.signUP(signupDto);
        return BaseResponse.ok(SuccessCode.USER_CREATE_SUCCESS,signupDto);
    }
}
