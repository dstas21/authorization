package com.triangle.authorization.controller;

import com.triangle.authorization.dto.AuthenticationRequestDto;
import com.triangle.authorization.dto.AuthenticationResponseDto;
import com.triangle.authorization.model.User;
import com.triangle.authorization.security.jwt.JwtTokenProvider;
import com.triangle.authorization.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер формирование jwt токена для зарегистрированного пользователя
 */
@RestController
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtTokenProvider jwtTokenProvider,
                                        UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    /**
     * Получение jwt токена для пользователя
     *
     * @param requestDto dto регистрации пользователя
     */
    @PostMapping("api/auth/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, requestDto.getPassword())
            );
            User user = userService.getByUsername(username);
            String token = jwtTokenProvider.createToken(user);

            AuthenticationResponseDto response = new AuthenticationResponseDto(username, token);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Некорректный логин или пароль");
        }
    }
}
