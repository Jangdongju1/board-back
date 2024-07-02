package com.persnal.boardback.dto.response.board;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.obj.CommentListItem;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.repository.reusltSet.GetCommentListResultSet;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetCommentListResponse extends ResponseDto {
    List<CommentListItem> commentList;

    // 성공시 리스트를 세팅.
    private GetCommentListResponse(List<GetCommentListResultSet> list) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = CommentListItem.setList(list);
    }

    public static ResponseEntity<GetCommentListResponse> success(List<GetCommentListResultSet> list){
        GetCommentListResponse response = new GetCommentListResponse(list);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<ResponseDto> notExistBoard(){
        ResponseDto response = new ResponseDto(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }



}
