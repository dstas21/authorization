package com.triangle.authorization.repository;

import com.triangle.authorization.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для сущности пользователь {@link User}
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Поиск пользователя по его уникальному логину
     *
     * @param username уникальный логин
     */
    Optional<User> findByUsername(String username);

    /**
     * Поиск пользователя по его почте
     *
     * @param email почта
     */
    Optional<User> findByEmail(String email);
}
