package com.persnal.boardback.dto.response.board;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PutFavoriteResponse extends ResponseDto {
    private PutFavoriteResponse(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PutFavoriteResponse> success(){
        PutFavoriteResponse response = new PutFavoriteResponse();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    public static ResponseEntity<ResponseDto> notExistBoard(){
        ResponseDto response = new ResponseDto(ResponseCode.NOT_EXIST_BOARD,ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto response = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
