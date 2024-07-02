package com.persnal.boardback.dto.response.user;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.response.ResponseDto;
import com.persnal.boardback.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetSignInUserResponse extends ResponseDto {
    private String userEmail;
    private String nickname;
    private String profileImg;

    private GetSignInUserResponse(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userEmail = userEntity.getUserEmail();
        this.nickname = userEntity.getNickname();
        this.profileImg = userEntity.getProfileImg();
    }

    public static ResponseEntity<GetSignInUserResponse> success(UserEntity userEntity) {
        GetSignInUserResponse result = new GetSignInUserResponse(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExist() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
