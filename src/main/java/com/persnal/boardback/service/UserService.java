package com.persnal.boardback.service;

import com.persnal.boardback.dto.response.user.GetSignInUserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponse> getSignInUser(String email);
}
