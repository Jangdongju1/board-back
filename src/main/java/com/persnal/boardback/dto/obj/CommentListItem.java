package com.persnal.boardback.dto.obj;

import com.persnal.boardback.repository.reusltSet.GetCommentListResultSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListItem {
    private String nickname;
    private String profileImg;
    private String writeDate;
    private String content;

    public CommentListItem(GetCommentListResultSet resultSet){
        this.nickname = resultSet.getNickname();
        this.profileImg = resultSet.getProfileImg();
        this.writeDate = resultSet.getWriteDate();
        this.content = resultSet.getContent();
    }
    public static List<CommentListItem> setList(List<GetCommentListResultSet> list){
        List<CommentListItem> commentList = new ArrayList<>();

        for (GetCommentListResultSet resultSet : list){
            CommentListItem commentListItem = new CommentListItem(resultSet);
            commentList.add(commentListItem);

        }
        return commentList;
    }
}
