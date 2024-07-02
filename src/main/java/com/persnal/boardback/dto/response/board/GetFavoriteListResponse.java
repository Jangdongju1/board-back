package com.persnal.boardback.dto.response.board;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.obj.FavoriteListItem;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.repository.reusltSet.GetFavoriteListResultSet;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Getter
public class GetFavoriteListResponse extends ResponseDto {
    private List<FavoriteListItem> favoriteList;

    private GetFavoriteListResponse(List<GetFavoriteListResultSet> list) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        favoriteList = FavoriteListItem.setList(list);
    }

    public static ResponseEntity<GetFavoriteListResponse> success(List<GetFavoriteListResultSet> list){
        GetFavoriteListResponse response = new GetFavoriteListResponse(list);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<ResponseDto> notExistBoard(){
        ResponseDto response = new ResponseDto(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
