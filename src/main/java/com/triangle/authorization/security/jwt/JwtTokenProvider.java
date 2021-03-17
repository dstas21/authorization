package com.triangle.authorization.security.jwt;

import com.triangle.authorization.model.Role;
import com.triangle.authorization.model.User;
import com.triangle.authorization.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * Предназначен для работы с Jwt токеном
 * использовался паттерн Provider
 */
@Component
public class JwtTokenProvider {

    /**
     * Секретное слово для генерации jwt токена, указано в application.properties
     */
    @Value("${jwt.token.secret}")
    private String secret;

    /**
     * Валидность jwt токена в миллисекундах, указано в application.properties
     */
    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Декодер паролей, который будет нужен в {@link UserService}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Предназначена для кодирования секретного слова после внедрения зависимостей, перед выполнением кода
     */
    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    /**
     * Создание Jwt токена на основе полей
     *
     * @param user пользователь
     */
    public String createToken(User user) {

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", getRoleNames(user.getRoles()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder() // создаем jwt токен в виде строки на основе
                   .setClaims(claims) // полей логина и ролей
                   .setIssuedAt(now) // даты создания
                   .setExpiration(validity) // и времени до которого будет валиден токен
                   .signWith(SignatureAlgorithm.HS256, secret) //кодируем по алгоритму HS256 и секретного слова
                   .compact();
    }

    /**
     * Проверка аутентификации по токену
     *
     * @param token токен
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Получение логина пользовател япо токену по токену
     *
     * @param token токен
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Анализ токена пришедшем в http запросе
     *
     * @param request http запрос
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (nonNull(bearerToken) && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    /**
     * Проверка валидации токена c использование класса Claims, который реализован в библиотеке Spring Security
     * для получения валидности токена по времени
     *
     * @param token токен
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            if (claimsJws.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("Jwt токен не валиден");
        }
    }

    /**
     * Получения названия ролей по списку ролей
     *
     * @param userRoles токен
     */
    private List<String> getRoleNames(List<Role> userRoles) {
        return userRoles.stream()
                        .map(Role::getName)
                        .collect(Collectors.toList());
    }
}
