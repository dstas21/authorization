package com.triangle.authorization.controller;

import com.triangle.authorization.api.UserRoleApi;
import com.triangle.authorization.dto.RoleDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Сервис для работы связи пользователя и ролей
 */
@RestController
public class UserRoleController {

    private final UserRoleApi api;

    public UserRoleController(UserRoleApi api) {
        this.api = api;
    }

    /**
     * Добавление роли к пользователю
     *
     * @param userId идентификатор пользователя
     * @param roleId идентификатор роли
     */
    @PostMapping("/api/admin/users/{userId}/roles/{roleId}")
    public HttpStatus add(@PathVariable Long userId,
                          @PathVariable Long roleId) {
        api.add(userId, roleId);
        return HttpStatus.NO_CONTENT;
    }

    /**
     * Поиск всех ролей по идентификатору пользователю
     *
     * @param userId идентификатор пользователя
     * @param page   страница
     * @param size   количество сущностей на страницу
     * @param sort   параметр сортироваки
     */
    @GetMapping("api/users/roles")
    public ResponseEntity<List<RoleDto>> get(@RequestParam(name = "userId") Long userId,
                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "50") int size,
                                             @RequestParam(name = "sort", defaultValue = "username") String sort) {
        return ResponseEntity.ok(api.get(userId, PageRequest.of(page, size, Sort.by(sort))));
    }

    /**
     * Удаление роли у пользователя
     *
     * @param userId идентификатор пользователя
     * @param roleId идентификатор роли
     */
    @DeleteMapping("/api/admin/users/{userId}/roles/{roleId}")
    public HttpStatus delete(@PathVariable Long userId,
                             @PathVariable Long roleId) {
        api.delete(userId, roleId);
        return HttpStatus.NO_CONTENT;
    }
}
