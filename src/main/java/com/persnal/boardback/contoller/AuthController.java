package com.persnal.boardback.contoller;

import com.persnal.boardback.dto.request.auth.SignInRequest;
import com.persnal.boardback.dto.request.auth.SignUpRequest;
import com.persnal.boardback.dto.response.auth.SignInResponse;
import com.persnal.boardback.dto.response.auth.SignUpResponse;
import com.persnal.boardback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    public final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponse> signUp(@Validated @RequestBody  SignUpRequest signUpRequest) {
        ResponseEntity<? super SignUpResponse> response = authService.signUp(signUpRequest);
        return response;

    }
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponse> signIn(@Validated @RequestBody SignInRequest signInRequest) {
        ResponseEntity<? super SignInResponse> response = authService.signIn(signInRequest);

        return response;
    }
}
