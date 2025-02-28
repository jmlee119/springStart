package com.study.boardPage.batch.infrastructure;

import com.study.boardPage.users.domain.Users;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InactiveUserItemReader implements ItemReader<Users> {
    private final UserBatchRepository userBatchRepository;
    private Iterator<Users> iterator;
    // iterator -> 데이터를 하나씩 반환하도록 설정

    @PostConstruct
    // @PostConstruct -> 빈이 초기화 된 후에 실행되는 메서드
    public void init() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        // 현재 시간에서 6개월을 빼서, 6개월 이전의 날짜를 계산
        List<Users> inactiveUsers = userBatchRepository.findByLastLoginBeforeAndStatus(sixMonthsAgo, 1);
        inactiveUsers.forEach(System.out::println);
        // 6개월 이상 로그인하지 않고, 상태가 1인 사용자
        this.iterator = inactiveUsers.iterator();
        // 조회된 사용자 리스트를 iterator 로 변환하여 필드에 할당, 데이터를 순차적으로 읽을 수 있음
    }
    @Override
    public Users read() {
        // ItemReader 에서 요구되는 메서드 , 배치 처리에서 데이터를 하나씩 읽음
        return iterator.hasNext() ? iterator.next() : null;
        // hasNext -> 더 이상 읽을 데이터가 있는지
        // 있으면 다음으로 읽고 없으면 null 로 종료
    }

}
