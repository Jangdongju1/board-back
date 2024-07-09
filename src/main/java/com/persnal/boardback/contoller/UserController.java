package com.persnal.boardback.contoller;

import com.persnal.boardback.dto.response.user.GetSignInUserResponse;
import com.persnal.boardback.dto.response.user.GetUserResponse;
import com.persnal.boardback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    // @AuthenticationPrincipal는 '필터에서' 토큰에 넣은 유저의 이메일을 가져올 수 있도록 하는 어노테이션임.
    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponse> getSignInUser(@AuthenticationPrincipal String email){
        ResponseEntity<? super GetSignInUserResponse> response = userService.getSignInUser(email);
        return response;
    }

    @GetMapping("/{email}")
    public ResponseEntity<? super GetUserResponse> getUser(@PathVariable("email") String email){
        ResponseEntity<? super GetUserResponse> response = userService.getUser(email);
        return response;
    }

}
