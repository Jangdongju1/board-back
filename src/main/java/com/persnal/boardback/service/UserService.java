package com.persnal.boardback.service;

import com.persnal.boardback.dto.request.user.PatchNicknameRequest;
import com.persnal.boardback.dto.request.user.PatchProfileImageRequest;
import com.persnal.boardback.dto.response.user.GetSignInUserResponse;
import com.persnal.boardback.dto.response.user.GetUserResponse;
import com.persnal.boardback.dto.response.user.PatchProfileImageResponse;
import com.persnal.boardback.dto.response.user.PatchUserNicknameResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<? super GetUserResponse> getUser(String email);
    ResponseEntity<? super GetSignInUserResponse> getSignInUser(String email);
    ResponseEntity<? super PatchUserNicknameResponse> patchNickname(PatchNicknameRequest req, String email);
    ResponseEntity<? super PatchProfileImageResponse> patchProfileImage(PatchProfileImageRequest req, String email);

}
