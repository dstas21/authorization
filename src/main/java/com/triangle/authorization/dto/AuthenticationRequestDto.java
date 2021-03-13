package com.triangle.authorization.dto;

import lombok.Data;

/**
 * Dto для регистрации пользователей
 */
@Data
public class AuthenticationRequestDto {

    /**
     * Логин
     */
    private String username;

    /**
     * Пароль
     */
    private String password;
}
