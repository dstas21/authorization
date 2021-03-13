package com.triangle.authorization.config;

import com.triangle.authorization.security.jwt.JwtConfigurer;
import com.triangle.authorization.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Предназначено для конфигурации безопасности http
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String ADMIN_ENDPOINT = " api/admin/**";
    private static final String LOGIN_ENDPOINT = " api/auth/login/**";

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable()
                    .csrf().disable() //отключаем защиту от взлома
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) /*сессию для каждого
                    пользователя не создаем*/
                    .and()
                    .authorizeRequests() //параметры авторизации запроса
                    .antMatchers(LOGIN_ENDPOINT).permitAll() //какие url доступны для всех
                    .antMatchers(ADMIN_ENDPOINT).hasRole(ADMIN_ROLE) //какие url доступны для пользователя с ролью ADMIN
                    .anyRequest().authenticated() //все оставшиеся url доступны аутентифицированным пользователям
                    .and()
                    .apply(new JwtConfigurer(jwtTokenProvider)); //создаем наш jwt конфигуратор
    }
}
