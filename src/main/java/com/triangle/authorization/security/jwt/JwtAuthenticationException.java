package com.triangle.authorization.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Ошибка аутентификации при истечения срока годности токена
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
