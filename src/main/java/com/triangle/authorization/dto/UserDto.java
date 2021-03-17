package com.triangle.authorization.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;

@Data
public class UserDto {

    /**
     * Уникальный логин
     */
    @NonNull
    private String username;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * Почта
     */
    @Email
    private String email;

    /**
     * Пароль
     */
    @NonNull
    private String password;
}
