package com.persnal.boardback.entity;

import com.persnal.boardback.common.Utils;
import com.persnal.boardback.dto.request.board.PatchBoardUpdateRequest;
import com.persnal.boardback.dto.request.board.PostBoardRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Table(name = "board")
public class BoardEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)  == Autoincrement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNum = 0;
    private String title = "";
    private String content = "";
    private String writeDate = "";
    private String nickname = "";
    private String boardTitleImg = "";
    private int favoriteCnt = 0;
    private int commentCnt = 0;
    private int viewCnt = 0;
    private String userEmail = "";
    private String writerProfileImg = "";

    public BoardEntity(PostBoardRequest req, String email, UserEntity userEntity) {
        String writeDate = Utils.getNowTime(LocalDateTime.now());
        List<String> boardImgList = req.getBoardImgList();

        this.title = req.getTitle();
        this.content = req.getContent();
        this.userEmail = email;
        this.writeDate = writeDate;
        this.nickname = userEntity.getNickname();
        this.writerProfileImg = userEntity.getProfileImg();
        if (!boardImgList.isEmpty()){
            boardTitleImg = boardImgList.get(0);
        }
    }

    // 조회수 증가 메서드
    public void increaseViewCnt() {
        this.viewCnt++;
    }

    public void increaseFavoriteCnt() {
        this.favoriteCnt++;
    }
    public void decreaseFavoriteCnt() {this.favoriteCnt--;}
    public void increaseCommentCnt() {this.commentCnt++;}

    public void patchBoard(PatchBoardUpdateRequest req){
        this.title = req.getTitle();
        this.content = req.getContent();
    }
}
