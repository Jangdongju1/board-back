package com.persnal.boardback.contoller;

import com.persnal.boardback.dto.request.user.PatchNicknameRequest;
import com.persnal.boardback.dto.request.user.PatchProfileImageRequest;
import com.persnal.boardback.dto.response.user.GetSignInUserResponse;
import com.persnal.boardback.dto.response.user.GetUserResponse;
import com.persnal.boardback.dto.response.user.PatchProfileImageResponse;
import com.persnal.boardback.dto.response.user.PatchUserNicknameResponse;
import com.persnal.boardback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    // @AuthenticationPrincipal는 '필터에서' 토큰에 넣은 유저의 이메일을 가져올 수 있도록 하는 어노테이션임.
    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponse> getSignInUser(@AuthenticationPrincipal String email) {
        ResponseEntity<? super GetSignInUserResponse> response = userService.getSignInUser(email);
        return response;
    }

    @GetMapping("/{email}")
    public ResponseEntity<? super GetUserResponse> getUser(@PathVariable("email") String email) {
        ResponseEntity<? super GetUserResponse> response = userService.getUser(email);
        return response;
    }

    @PatchMapping("/nickname")
    public ResponseEntity<? super PatchUserNicknameResponse> patchNickname(
            @Validated @RequestBody PatchNicknameRequest req,
            @AuthenticationPrincipal String email) {
        ResponseEntity<? super PatchUserNicknameResponse> response = userService.patchNickname(req, email);
        return response;
    }

    @PatchMapping("/profile-image")
    public ResponseEntity<? super PatchProfileImageResponse> patchProfileImage(
            @Validated @RequestBody PatchProfileImageRequest req,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PatchProfileImageResponse> response = userService.patchProfileImage(req, email);
        return response;
    }
}
