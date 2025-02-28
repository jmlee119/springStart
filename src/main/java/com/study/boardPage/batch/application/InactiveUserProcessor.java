package com.study.boardPage.batch.application;

import com.study.boardPage.users.domain.Users;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class InactiveUserProcessor implements ItemProcessor<Users,Users> {
    @Override
    public Users process(Users users) {
        users.setStatus(4);
        System.out.println("Update USer : " + users);
        return users;
    }

}
