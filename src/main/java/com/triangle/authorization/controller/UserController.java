package com.triangle.authorization.controller;

import com.triangle.authorization.api.UserApi;
import com.triangle.authorization.dto.UserDto;
import com.triangle.authorization.model.User;
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
 * Сервис для сущности пользоателей {@link User}
 */
@RestController
public class UserController {

    private final UserApi userApi;

    public UserController(UserApi userApi) {
        this.userApi = userApi;
    }

    /**
     * Создаем пользователя
     *
     * @param userDto dto пользователь
     */
    @PostMapping("api/admin/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userApi.create(userDto));
    }

    /**
     * Получаем всех пользователей
     *
     * @param page страница
     * @param size количество сущностей на страницу
     * @param sort параметр сортироваки
     */
    @GetMapping("/api/users")
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "50") int size,
                                                @RequestParam(name = "sort", defaultValue = "username") String sort) {
        return ResponseEntity.ok(userApi.getAll(PageRequest.of(page, size, Sort.by(sort))));
    }

    /**
     * Получаем пользователя по id
     *
     * @param id идентификатор
     */
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userApi.getById(id));
    }

    /**
     * Обновляем пользователя
     *
     * @param id      идентификатор пользователя
     * @param userDto dto для обновленные пользователя
     */
    @PutMapping("/api/admin/users/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id,
                                          @RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userApi.update(id, userDto));
    }

    /**
     * Удаляем пользователя по id
     *
     * @param id идентификатор
     */
    @DeleteMapping("/api/admin/users/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        userApi.delete(id);
        return HttpStatus.NO_CONTENT;
    }
}

