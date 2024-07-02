package com.persnal.boardback.dto.obj;

import com.persnal.boardback.entity.BoardEntity;
import com.persnal.boardback.entity.BoardListViewEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardListItem {
    private int boardNum;
    private String title;
    private String content;
    private String writeDate;
    private String nickname;
    private String boardTitleImg;
    private int favoriteCnt;
    private int commentCnt;
    private int viewCnt;
    private String writerProfileImg;


    public BoardListItem(BoardEntity boardListViewEntity) {
        this.boardNum = boardListViewEntity.getBoardNum();
        this.title = boardListViewEntity.getTitle();
        this.content = boardListViewEntity.getContent();
        this.writeDate = boardListViewEntity.getWriteDate();
        this.nickname = boardListViewEntity.getNickname();
        this.boardTitleImg = boardListViewEntity.getBoardTitleImg();
        this.favoriteCnt = boardListViewEntity.getFavoriteCnt();
        this.commentCnt = boardListViewEntity.getCommentCnt();
        this.viewCnt = boardListViewEntity.getViewCnt();
        this.writerProfileImg = boardListViewEntity.getWriterProfileImg();
    }

    public static List<BoardListItem> getList(List<BoardEntity> boardEntities){
        List<BoardListItem> list  = new ArrayList<>();
        for (BoardEntity entity : boardEntities) {
            BoardListItem  item = new BoardListItem(entity);
            list.add(item);
        }
        return list;
    }

}
