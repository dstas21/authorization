package com.triangle.authorization.controller;

import com.triangle.authorization.api.RoleApi;
import com.triangle.authorization.dto.RoleDto;
import com.triangle.authorization.model.Role;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Сервис для сущности роли {@link Role}
 */
@RestController
public class RoleController {

    private final RoleApi roleApi;

    public RoleController(RoleApi roleApi) {
        this.roleApi = roleApi;
    }

    /**
     * Создаем роль
     *
     * @param roleDto dto роли
     */
    @PostMapping("/api/admin/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoleDto> create(@RequestBody @Valid RoleDto roleDto) {
        return ResponseEntity.ok(roleApi.create(roleDto));
    }

    /**
     * Получаем все роли
     *
     * @param page страница
     * @param size количество сущностей на страницу
     * @param sort параметр сортироваки
     */
    @GetMapping("api/roles")
    public ResponseEntity<List<RoleDto>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "50") int size,
                                                @RequestParam(name = "sort", defaultValue = "name") String sort) {
        return ResponseEntity.ok(roleApi.getAll(PageRequest.of(page, size, Sort.by(sort))));
    }

    /**
     * Получаем роли по id
     *
     * @param id идентификатор
     */
    @GetMapping("api/roles/{id}")
    public ResponseEntity<RoleDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roleApi.getById(id));
    }

    /**
     * Обновляем роль
     *
     * @param id      идентификатор роли
     * @param roleDto dto для обновленные роли
     */
    @PutMapping("/api/admin/roles/{id}")
    public ResponseEntity<RoleDto> update(@PathVariable Long id,
                                          @RequestBody @Valid RoleDto roleDto) {
        return ResponseEntity.ok(roleApi.update(id, roleDto));
    }

    /**
     * Удаляем роль по id
     *
     * @param id идентификатор
     */
    @DeleteMapping("/api/admin/roles/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        roleApi.delete(id);
        return HttpStatus.NO_CONTENT;
    }
}
