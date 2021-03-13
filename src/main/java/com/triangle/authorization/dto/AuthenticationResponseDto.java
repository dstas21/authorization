package com.triangle.authorization.dto;

import lombok.Data;

/**
 * Dto для регистрации пользователей
 */
@Data
public class AuthenticationResponseDto {

    public AuthenticationResponseDto() {}

    public AuthenticationResponseDto(String username, String token) {
        this.username = username;
        this.token = token;
    }

    /**
     * Логин
     */
    private String username;

    /**
     * Токен
     */
    private String token;
}
