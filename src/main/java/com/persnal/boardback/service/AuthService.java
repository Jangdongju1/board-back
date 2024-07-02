package com.persnal.boardback.service;

import com.persnal.boardback.dto.request.auth.SignInRequest;
import com.persnal.boardback.dto.request.auth.SignUpRequest;
import com.persnal.boardback.dto.response.auth.SignInResponse;
import com.persnal.boardback.dto.response.auth.SignUpResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<? super SignUpResponse> signUp(SignUpRequest req);
    ResponseEntity<? super SignInResponse> signIn(SignInRequest req);

}
