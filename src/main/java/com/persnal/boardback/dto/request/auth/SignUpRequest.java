package com.persnal.boardback.dto.request.auth;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    //@NotBlank  @NotEmpty : null 및 공백 문자열에 대한 유효성 검사.
    //@Email : 이메일 형식에 대한 유효성 검사.

    @Email
    @NotNull
    @NotBlank(message = "이메일은 필수값 입니다..")
    private String userEmail;
    @NotBlank @NotNull
    @Size(min = 8, max = 20 , message = "비밀번호는 8~20자 내외입니다.")
    private String password;
    @NotBlank(message = "닉네임은 필수값 입니다.") @NotNull
    private String nickname;
    @NotBlank(message = "전화번호는 필수값 입니다.") @NotNull
    @Pattern(regexp = "^[0-9]{11,13}$")
    private String telNumber;
    @NotBlank(message = "주소는 필수값 입니다.")
    private String address;
    private String addressDetail;
    private String profileImg;
    @NotNull
    @AssertTrue
    private Boolean agreedPersonal;
}
