package com.study.boardPage.batch.infrastructure;

import com.study.boardPage.users.domain.Users;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserBatchRepository extends JpaRepository<Users, Long> {
    // findBy = where 문
    // LastLoginBefore lastlogin 필드보다 이전인 경우
    // And
    // StatusNot  status 값이 특정값이 아닐때
        List<Users> findByLastLoginBeforeAndStatus(LocalDateTime lastLogin, int status);
//    @Query("SELECT u FROM Users u WHERE u.status = 1 AND u.lastLogin <= :sixMonthsAgo")
//    List<Users> findInactiveUsers(@Param("sixMonthsAgo") LocalDateTime sixMonthsAgo);
}
