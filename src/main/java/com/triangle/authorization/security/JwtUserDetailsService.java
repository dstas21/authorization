package com.triangle.authorization.security;

import com.triangle.authorization.model.User;
import com.triangle.authorization.security.jwt.JwtUser;
import com.triangle.authorization.security.jwt.JwtUserFactory;
import com.triangle.authorization.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Реализвания сервиса для сущности пользователя для Spring Security {@link JwtUser}
 */
@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Поиск пользователя по логину для создания пользователя для Spring Security {@link JwtUser}
     *
     * @param username логин
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("Пользователь по username = {}, получен для преобразоания в JwtUser", username);
        return jwtUser;
    }
}
