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
public class GetLatestBoardListResponse extends ResponseDto {
    private List<BoardListItem> latestBoardList;

    private GetLatestBoardListResponse(List<BoardEntity> entities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        latestBoardList = BoardListItem.getList(entities);
    }

    public static ResponseEntity<GetLatestBoardListResponse> success(List<BoardEntity> entities) {
        GetLatestBoardListResponse response = new GetLatestBoardListResponse(entities);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
