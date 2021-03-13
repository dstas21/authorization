package com.triangle.authorization.service;

import com.triangle.authorization.model.Role;
import com.triangle.authorization.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для связи пользователя и книг
 */
@Service
@Slf4j
public class UserRoleService {

    private final UserService userService;
    private final RoleService roleService;

    public UserRoleService(UserService userService,
                           RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Добавляет роль к пользователю
     *
     * @param userId идентификатор пользователя
     * @param roleId идентификатор роли
     */
    public void add(Long userId, Long roleId) {
        updateDependentEntities(userId, roleId, false);
        log.info("Роль с id = {} добавлена к пользователю с id = {}", roleId, userId);
    }

    /**
     * Получение всех ролей пользователя
     *
     * @param userId   идентификатор пользователя
     * @param pageable пагинация для полученных ролей
     */
    public Page<Role> get(Long userId, Pageable pageable) {
        Page<Role> roles = roleService.getByUser(userService.getById(userId), pageable);
        log.info("Ролей пользователя найдено {}", roles.getSize());
        return roles;
    }

    /**
     * Удаляет роль у пользователя
     *
     * @param userId идентификатор пользователя
     * @param roleId идентификатор роли
     */
    public void delete(Long userId, Long roleId) {
        updateDependentEntities(userId, roleId, true);
        log.info("Роль с id = {} удалена у пользователя с id = {}", roleId, userId);

    }

    /**
     * Обновляем связь роли с автором
     *
     * @param userId идентификатор пользователя
     * @param roleId идентификатор роли
     * @param remove признак обновления, если remove is true то удаляем книгу у автора, иначе добавляем
     */
    private void updateDependentEntities(Long userId, Long roleId, boolean remove) {
        User user = userService.getById(userId);
        Role role = roleService.getById(roleId);

        if (remove) {
            user.getRoles().remove(role);
        } else {
            user.getRoles().add(role);
        }
        userService.update(user);
    }
}
