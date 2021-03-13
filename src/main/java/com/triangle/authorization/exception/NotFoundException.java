package com.triangle.authorization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Икслючение не найдена сущность
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private Long id;
    private String name;

    public NotFoundException(Long id) {
        super(String.format("Сущность с id = %d не найдена", id));
        this.id = id;
    }

    public NotFoundException(String name) {
        super(String.format("Сущность с названием = %s не найдена", name));
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
