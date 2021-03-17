package com.triangle.authorization.api;

import com.triangle.authorization.dto.RoleDto;
import com.triangle.authorization.mapper.RoleMapper;
import com.triangle.authorization.service.UserRoleService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Api для работы связи автора и книг
 */
@Component
public class UserRoleApi {

    private final UserRoleService service;
    private final RoleMapper mapper;

    public UserRoleApi(UserRoleService service,
                       RoleMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Добавление роли к пользователю
     *
     * @param userId идентификатор пользователя
     * @param roleId идентификатор роли
     */
    public void add(Long userId, Long roleId) {
        service.add(userId, roleId);
    }

    /**
     * Поиск всех ролей по идентификатору пользователю
     *
     * @param userId   идентификатор пользователя
     * @param pageable пагинация для получения ролей
     */
    public List<RoleDto> get(Long userId, Pageable pageable) {
        return mapper.toDtoList(service.get(userId, pageable));
    }

    /**
     * Удаление роли у пользователю
     *
     * @param userId идентификатор пользователя
     * @param roleId идентификатор роли
     */
    public void delete(Long userId, Long roleId) {
        service.delete(userId, roleId);
    }
}
