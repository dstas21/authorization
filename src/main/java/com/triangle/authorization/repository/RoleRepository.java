package com.triangle.authorization.repository;

import com.triangle.authorization.model.Role;
import com.triangle.authorization.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для сущности роль {@link Role}
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Поиск роли по названию
     *
     * @param name название
     */
    Optional<Role> findByName(String name);

    /**
     * Поиск ролей по пользователю
     *
     * @param user пользоатель
     */
    Page<Role> findByUsers(User user, Pageable pageable);
}
