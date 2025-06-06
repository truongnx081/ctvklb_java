package com.example.keycloak.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String AccessToken;
    private int ExpiresIn;
    private String RefreshToken;
    private String TokenType;
}
