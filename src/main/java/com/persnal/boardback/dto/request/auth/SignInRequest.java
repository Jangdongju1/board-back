package com.persnal.boardback.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequest {
    @NotBlank
    private String userEmail;
    @NotBlank
    private String password;
}
