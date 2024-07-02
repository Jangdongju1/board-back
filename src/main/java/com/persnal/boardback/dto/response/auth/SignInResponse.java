package com.persnal.boardback.dto.response.auth;

import com.persnal.boardback.common.ResponseCode;
import com.persnal.boardback.common.ResponseMessage;
import com.persnal.boardback.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponse extends ResponseDto {
    private String token;
    private int expireTime;

    public SignInResponse(String token){ // 부모의 생성자를 실행할 수 있도록.
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        this.expireTime = 3600; // 만료시간은 1시간.
    }

    public static ResponseEntity<SignInResponse> success(String token){
        SignInResponse result = new SignInResponse(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> failure(){
        ResponseDto result = new ResponseDto(ResponseCode.SIGN_IN_FAILED,ResponseMessage.SIGN_IN_FAILED);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

}
