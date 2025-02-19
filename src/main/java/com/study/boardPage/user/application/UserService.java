package com.study.boardPage.user.application;

import com.study.boardPage.user.domain.User;
import com.study.boardPage.user.dto.SignupDto;
import com.study.boardPage.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public SignupDto signUP(SignupDto signupDto) {
        User user = new User();
        user.setEmail(signupDto.getEmail());
        user.setPassword(signupDto.getPassword());
        user.setNickname(signupDto.getNickname());
        userRepository.save(user);
        return signupDto;
    }

}
