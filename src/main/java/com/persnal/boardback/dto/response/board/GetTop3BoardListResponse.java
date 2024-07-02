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
public class GetTop3BoardListResponse extends ResponseDto {
    List<BoardListItem> top3BoardList;
    private GetTop3BoardListResponse(List<BoardEntity> boardEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        top3BoardList = BoardListItem.getList(boardEntities);
    }

    public static ResponseEntity<GetTop3BoardListResponse> success(List<BoardEntity> boardEntities) {
        GetTop3BoardListResponse response = new GetTop3BoardListResponse(boardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
