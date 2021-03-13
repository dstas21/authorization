package com.triangle.authorization.security.jwt;

import com.triangle.authorization.model.Role;
import com.triangle.authorization.model.Status;
import com.triangle.authorization.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Преобразование пользователя этого сервиса {@link User} в пользователя для Spring Security {@link JwtUser}
 * использовался паттерн Factory
 */
public final class JwtUserFactory {

    /**
     * Преобразоание пользователя {@link User} в пользователя для Spring Security {@link JwtUser}
     *
     * @param user пользователь
     */
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                mapToGrantedAuthorities(user.getRoles())
        );
    }

    /**
     * Преобразоание ролей {@link Role} в роли для Spring Security
     *
     * @param userRoles
     * @return
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList());
    }
}
