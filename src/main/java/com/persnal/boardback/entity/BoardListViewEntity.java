package com.persnal.boardback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board_list_view")
@Table(name = "board_list_view")
public class BoardListViewEntity {
    @Id
    private int boardNum = 0;
    private String title = "";
    private String content = "";
    private String boardTitleImg = "";
    private int viewCnt = 0;
    private int favoriteCnt = 0;
    private int commentCnt = 0;
    private String userEmail = "";
    private String nickname = "";
    private String writerProfileImg = "";
    private String writeDate = "";
}
