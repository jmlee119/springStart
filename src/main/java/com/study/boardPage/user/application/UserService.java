package com.study.boardPage.user.application;

import com.study.boardPage.global.exception.BaseException;
import com.study.boardPage.global.response.ErrorCode;
import com.study.boardPage.user.domain.User;
import com.study.boardPage.user.dto.SignupDto;
import com.study.boardPage.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public SignupDto signUp(SignupDto signupDto) {
        Optional<User> email = userRepository.findByEmail(signupDto.getEmail());
        if (email.isPresent()) {
            throw new BaseException(ErrorCode.USER_EMAIL_EXIST_FAILED);
        }
        Optional<User> nickname = userRepository.findByNickname(signupDto.getNickname());
        if (nickname.isPresent()) {
            throw new BaseException(ErrorCode.USER_NICKNAME_EXIST_FAILED);
        }
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        if (!Pattern.matches(passwordRegex, signupDto.getPassword())) {
            throw new BaseException(ErrorCode.USER_PASSWORD_VALIDATION_FAILED);
        }
        User user = new User();
        user.setEmail(signupDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDto.getPassword()));
        user.setNickname(signupDto.getNickname());
        user.setStatus(1);
        userRepository.save(user);
        return signupDto;
    }

}
