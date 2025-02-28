package com.study.boardPage.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int boardType;
    private String title;
    private String content;
    private LocalDateTime rgdt;

    @ManyToOne
    private Users users;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.rgdt = LocalDateTime.now();
    }
}