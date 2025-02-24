package com.study.boardPage.users.domain;

import com.study.boardPage.board.domain.Board;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, length = 20)
    private String nickname;
    
    // status 1 활성 / 2 탈퇴 / 3 관리자
    private int status;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Board> board;
}
