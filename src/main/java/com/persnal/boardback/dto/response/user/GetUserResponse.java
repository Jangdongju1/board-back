package com.persnal.boardback.dto.response.user;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetUserResponse extends ResponseDto {
    private String email = "";
    private String nickname = "";
    private String profileImg = "";

    private GetUserResponse(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getUserEmail();
        this.nickname = userEntity.getNickname();
        this.profileImg = userEntity.getProfileImg();
    }

    public static ResponseEntity<GetUserResponse> success(UserEntity userEntity) {
        GetUserResponse response = new GetUserResponse(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto response = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
