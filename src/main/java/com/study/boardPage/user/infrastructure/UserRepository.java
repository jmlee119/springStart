package com.study.boardPage.user.infrastructure;

import com.study.boardPage.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
