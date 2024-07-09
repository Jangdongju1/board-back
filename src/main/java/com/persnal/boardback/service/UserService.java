package com.persnal.boardback.service;

import com.persnal.boardback.dto.response.user.GetSignInUserResponse;
import com.persnal.boardback.dto.response.user.GetUserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<? super GetUserResponse> getUser(String email);
    ResponseEntity<? super GetSignInUserResponse> getSignInUser(String email);
}
