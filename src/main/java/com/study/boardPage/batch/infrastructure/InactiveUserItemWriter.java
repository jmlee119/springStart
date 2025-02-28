package com.study.boardPage.batch.infrastructure;

import com.study.boardPage.users.domain.Users;
import com.study.boardPage.users.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InactiveUserItemWriter implements ItemWriter<Users> {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void write(Chunk<? extends Users> chunk) throws Exception {
        List<Users> users = (List<Users>) chunk.getItems(); // Chunk에서 List로 변환
        users.forEach(user -> System.out.println("User to save: " + user));
        userRepository.saveAll(users); // 사용자 저장
    }
}

