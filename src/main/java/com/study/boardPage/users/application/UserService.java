package com.study.boardPage.users.application;

import com.study.boardPage.global.exception.BaseException;
import com.study.boardPage.global.response.ErrorCode;
import com.study.boardPage.security.JwtTokenProvider;
import com.study.boardPage.users.domain.Users;
import com.study.boardPage.users.dto.SignInDto;
import com.study.boardPage.users.dto.SignupDto;
import com.study.boardPage.users.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public SignupDto signUp(SignupDto signupDto) {
        Optional<Users> email = userRepository.findByEmail(signupDto.getEmail());
        if (email.isPresent()) {
            throw new BaseException(ErrorCode.USER_EMAIL_EXIST_FAILED);
        }
        Optional<Users> nickname = userRepository.findByNickname(signupDto.getNickname());
        if (nickname.isPresent()) {
            throw new BaseException(ErrorCode.USER_NICKNAME_EXIST_FAILED);
        }
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        if (!Pattern.matches(passwordRegex, signupDto.getPassword())) {
            throw new BaseException(ErrorCode.USER_PASSWORD_VALIDATION_FAILED);
        }
        Users users = new Users();
        users.setEmail(signupDto.getEmail());
        users.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        users.setNickname(signupDto.getNickname());
        users.setStatus(1);

        userRepository.save(users);
        return signupDto;
    }

    public String login(SignInDto signInDto) {
        Optional<Users> optionalUser = userRepository.findByEmail(signInDto.getEmail());

        Users user = optionalUser.orElseThrow(() -> new BaseException(ErrorCode.USER_LOGIN_ERROR_FAILED));

        if (passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);

            String accessToken = jwtTokenProvider.getAccessToken(user.getId()); // 토큰 생성
            return accessToken;
        } else {
            throw new BaseException(ErrorCode.USER_LOGIN_ERROR_FAILED);
        }
    }


}
