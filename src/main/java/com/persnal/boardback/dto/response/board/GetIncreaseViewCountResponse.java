package com.persnal.boardback.dto.response.board;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetIncreaseViewCountResponse extends ResponseDto {
    private GetIncreaseViewCountResponse() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<GetIncreaseViewCountResponse> success() {
        GetIncreaseViewCountResponse response = new GetIncreaseViewCountResponse();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<ResponseDto> notExistBoard() {
        ResponseDto response = new ResponseDto(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
