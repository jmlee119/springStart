package com.study.boardPage.users.infrastructure;

import com.study.boardPage.users.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    // Long 은 pk 타입 추가하는 것
    Optional<Users> findByEmail(String email);
    Optional<Users> findByNickname(String nickname);
}
