package com.triangle.authorization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исклчение дубликат сущности
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    private String field;

    public ConflictException(String field) {
        super(String.format("Сущность с %s уже существует", field));
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
