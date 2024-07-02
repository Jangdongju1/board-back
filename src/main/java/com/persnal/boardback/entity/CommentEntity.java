package com.persnal.boardback.entity;

import com.persnal.boardback.common.Utils;
import com.persnal.boardback.dto.request.board.PostCommentRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comment")
@Table(name = "comment")
public class CommentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentNum = 0;
    private String userEmail = "";
    private int boardNum = 0;
    private String content = "";
    private String writeDate = "";

    //대댓글 관련 필드 추후 적용예정
    private int commentLevel = 0;
    private int commentOrder = 0;
    private int commentGroup = 0;
    private int parentNum = 0;
    private int childNum = 0;

    public CommentEntity(PostCommentRequest req, String userEmail, int boardNum){
        String writeDate = Utils.getNowTime(LocalDateTime.now());

        this.content = req.getComment();
        this.userEmail = userEmail;
        this.boardNum = boardNum;
        this.writeDate = writeDate;
    }


}
