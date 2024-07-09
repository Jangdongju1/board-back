package com.persnal.boardback.dto.response.board;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.obj.BoardListItem;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.entity.BoardEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetSearchBoardListResponse extends ResponseDto {
    private final List<BoardListItem> searchList;

    private GetSearchBoardListResponse(List<BoardEntity> boardListEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.searchList = BoardListItem.getList(boardListEntities);
    }

    public static ResponseEntity<GetSearchBoardListResponse> success(List<BoardEntity> boardListEntities) {
        GetSearchBoardListResponse response = new GetSearchBoardListResponse(boardListEntities);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
