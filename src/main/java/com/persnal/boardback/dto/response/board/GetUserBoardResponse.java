package com.persnal.boardback.dto.response.board;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.obj.BoardListItem;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.entity.BoardEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetUserBoardResponse extends ResponseDto {
    private List<BoardListItem> userBoardList;

    private GetUserBoardResponse(List<BoardEntity> boardEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        userBoardList = new ArrayList<>();
        userBoardList = BoardListItem.getList(boardEntities);
    }

    public static ResponseEntity<GetUserBoardResponse> success(List<BoardEntity> boardEntities) {
        GetUserBoardResponse response = new GetUserBoardResponse(boardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto response = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
